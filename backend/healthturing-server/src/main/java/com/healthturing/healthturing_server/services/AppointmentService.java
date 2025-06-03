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
import com.healthturing.healthturing_server.mapper.AppointmentMapper;
import com.healthturing.healthturing_server.models.Appointment;
import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.repositories.AppointmentRepository;
import com.healthturing.healthturing_server.repositories.PatientRepository;

@Service
public class AppointmentService {

    private static final LocalTime START_TIME = LocalTime.of(9, 0);
    private static final LocalTime END_TIME = LocalTime.of(15, 0);
    private static final Duration SLOT_DURATION = Duration.ofHours(1);

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public List<AppointmentDTO> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        return AppointmentMapper.toDtoList(appointments);
    }

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

    public Appointment createAppointment(Long patientId, Long doctorId, LocalDate date, LocalTime startTime,
            String reason) {
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
        return appointmentRepository.save(appointment);
    }

}