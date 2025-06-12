package com.healthturing.healthturing_server.services;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.dto.AppointmentDTO;
import com.healthturing.healthturing_server.exceptions.AppointmentLimitException;
import com.healthturing.healthturing_server.mapper.AppointmentMapper;
import com.healthturing.healthturing_server.models.Appointment;
import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.repositories.AppointmentRepository;
import com.healthturing.healthturing_server.repositories.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private static final LocalTime START_TIME = LocalTime.of(9, 0);
    private static final LocalTime END_TIME = LocalTime.of(15, 0);
    private static final Duration SLOT_DURATION = Duration.ofHours(1);

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final EmailTemplateService emailTemplateService;

    /**
     * Devuelve una lista de citas por id de paciente.
     */
    public List<AppointmentDTO> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        return AppointmentMapper.toDtoList(appointments);
    }

    /**
     * Devuelve todas las citas de los pacientes asignados al doctor con ID proporcionado
     * @param doctorId
     * @return List<AppointmentDTO>
     */
    public List<AppointmentDTO> getAppointmentsByDoctorId(Long doctorId) {
        List<Patient> patients = patientRepository.findByDoctorId(doctorId);
        List<Appointment> allAppointments = new ArrayList<>();
        for (Patient patient : patients) {
            allAppointments.addAll(patient.getAppointments());
        }
        return AppointmentMapper.toDtoList(allAppointments);
    }

    /**
     * Busca slots (1h) entre los rangos indicados disponibles por id doctor
     * en una fecha concreta, omitiendo findes de semana.
     * @param doctorId
     * @param date
     * @return List<LocalTime>
     */
    public List<LocalTime> getAvailableSlotsForDoctor(Long doctorId, LocalDate date) {
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusDays(7);
        if (date.isBefore(today) || date.isAfter(maxDate)) {
            return new ArrayList<>();
        }

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return new ArrayList<>();
        }

        List<LocalTime> allSlots = new ArrayList<>();
        LocalTime slot = START_TIME;
        while (!slot.isAfter(END_TIME.minus(SLOT_DURATION))) {
            allSlots.add(slot);
            slot = slot.plus(SLOT_DURATION);
        }

        List<Appointment> reservedAppointments = appointmentRepository.findByDoctorIdAndDate(doctorId, date);

        Set<LocalTime> reservedSlots = reservedAppointments.stream()
                .map(Appointment::getStartTime)
                .collect(Collectors.toSet());

        LocalTime now = LocalTime.now();
        boolean isToday = date.equals(today);

        return allSlots.stream()
                .filter(time -> !reservedSlots.contains(time))
                .filter(time -> !isToday || time.isAfter(now))
                .collect(Collectors.toList());
    }

    /**
     * Crear una cita, después de las restricciones y comprobar que hay slot disponible,
     * y envía un correo de confirmación con los datos de esta al usuario.
     * @param patientId
     * @param doctorId
     * @param date
     * @param startTime
     * @param reason
     * @return Appointment
     */
    public Appointment createAppointment(Long patientId, Long doctorId, LocalDate date, LocalTime startTime,
            String reason) {

        int patientAppointments = appointmentRepository.countByPatientId(patientId);
        if (patientAppointments >= 3) {
            throw new AppointmentLimitException("No puedes tener más de 3 reservas de citas activas.");
        }

        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusDays(7);
        if (date.isBefore(today) || date.isAfter(maxDate)) {
            throw new IllegalArgumentException("La fecha debe estar dentro de la próxima semana.");
        }

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("No se pueden reservar citas en fines de semana.");
        }

        if (date.equals(LocalDate.now()) && startTime.isBefore(LocalTime.now())) {
            throw new IllegalArgumentException("No se pueden reservar horarios pasados.");
        }

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));
        if (patient.getDoctor().getId() != doctorId) {
            throw new IllegalArgumentException("El paciente no está asignado a este doctor");
        }

        List<Appointment> existingAppointments = appointmentRepository
                .findByDoctorIdAndDate(doctorId, date);
        boolean slotTaken = existingAppointments.stream()
                .anyMatch(a -> a.getStartTime().equals(startTime));
        if (slotTaken) {
            throw new IllegalArgumentException("El slot ya está reservado");
        }

        if (startTime.isBefore(START_TIME) || startTime.isAfter(END_TIME.minus(SLOT_DURATION))) {
            throw new IllegalArgumentException("El horario seleccionado no es válido");
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDate(date);
        appointment.setStartTime(startTime);
        appointment.setEndTime(startTime.plusHours(1));
        appointment.setReason(reason);
        Appointment saved = appointmentRepository.save(appointment);

        try {
            this.emailTemplateService.sendConfirmationAppointmentEmail(
                    patient.getUser().getEmail(),
                    patient.getName(),
                    date,
                    startTime,
                    reason);
        } catch (Exception e) {
            System.err.println("Error enviando correo de confirmación: " + e.getMessage());
        }

        return saved;
    }

}