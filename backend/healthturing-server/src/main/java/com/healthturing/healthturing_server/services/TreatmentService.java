package com.healthturing.healthturing_server.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.dto.TreatmentCreateDTO;
import com.healthturing.healthturing_server.dto.TreatmentDTO;
import com.healthturing.healthturing_server.dto.TreatmentUpdateDTO;
import com.healthturing.healthturing_server.mapper.TreatmentMapper;
import com.healthturing.healthturing_server.models.Medicament;
import com.healthturing.healthturing_server.models.Patient;
import com.healthturing.healthturing_server.models.Treatment;
import com.healthturing.healthturing_server.repositories.MedicamentRepository;
import com.healthturing.healthturing_server.repositories.PatientRepository;
import com.healthturing.healthturing_server.repositories.TreatmentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final PatientRepository patientRepository;
    private final MedicamentRepository medicamentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository, PatientRepository patientRepository,
            MedicamentRepository medicamentRepository) {
        this.treatmentRepository = treatmentRepository;
        this.patientRepository = patientRepository;
        this.medicamentRepository = medicamentRepository;
    }

    public List<TreatmentDTO> getTreatmentsByPatientId(Long patientId) {
        List<Treatment> treatments = treatmentRepository.findByPatientId(patientId);
        return TreatmentMapper.toDtoList(treatments);
    }

    public Treatment createTreatment(TreatmentCreateDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
        Medicament medicament = medicamentRepository.findById(dto.getMedicamentId())
                .orElseThrow(() -> new EntityNotFoundException("Medicamento no encontrado"));

        String duration;
        if (dto.getEndDate() == null) {
            duration = "Indefinido"; // O el texto que prefieras
        } else {
            duration = getDuration(dto.getStartDate(), dto.getEndDate());
        }

        Treatment treatment = new Treatment(
                dto.getName(),
                dto.getDescription(),
                dto.getStartDate(),
                dto.getEndDate(),
                duration,
                dto.getDosesPerPeriod(),
                patient,
                medicament);
        return treatmentRepository.save(treatment);
    }

    public boolean deleteTreatment(Long treatmentId) {
        if (!treatmentRepository.existsById(treatmentId)) {
            throw new EntityNotFoundException("Tratamiento no encontrado");
        }
        treatmentRepository.deleteById(treatmentId);
        return true;
    }

public Treatment updateTreatment(Long treatmentId, TreatmentUpdateDTO dto) {
    Treatment treatment = treatmentRepository.findById(treatmentId)
            .orElseThrow(() -> new EntityNotFoundException("Tratamiento no encontrado"));

    if (dto.getName() != null && dto.getName().trim().isEmpty()) {
        throw new IllegalArgumentException("El nombre no puede estar vacío");
    }
    if (dto.getDescription() != null && dto.getDescription().trim().isEmpty()) {
        throw new IllegalArgumentException("La descripción no puede estar vacía");
    }
    if (dto.getStartDate() != null && dto.getStartDate().toString().trim().isEmpty()) {
        throw new IllegalArgumentException("La fecha de inicio no puede estar vacía");
    }
    if (dto.getDosesPerPeriod() != null && dto.getDosesPerPeriod().trim().isEmpty()) {
        throw new IllegalArgumentException("Las dosis por periodo no pueden estar vacías");
    }

    treatment.setName(dto.getName());
    treatment.setDescription(dto.getDescription());
    treatment.setStartDate(dto.getStartDate());
    treatment.setEndDate(dto.getEndDate());
    treatment.setDosesPerPeriod(dto.getDosesPerPeriod());

    if (treatment.getEndDate() != null && treatment.getStartDate() != null
        && treatment.getEndDate().isBefore(treatment.getStartDate())) {
        throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
    }

    String duration = (treatment.getEndDate() == null)
            ? "Indefinida"
            : getDuration(treatment.getStartDate(), treatment.getEndDate());
    treatment.setDuration(duration);

    return treatmentRepository.save(treatment);
}

    public String getDuration(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        Period period = Period.between(startDate, endDate);

        StringBuilder sb = new StringBuilder();
        if (period.getYears() > 0) {
            sb.append(period.getYears()).append(period.getYears() == 1 ? " año " : " años ");
        }
        if (period.getMonths() > 0) {
            sb.append(period.getMonths()).append(period.getMonths() == 1 ? " mes " : " meses ");
        }
        int remDays = period.getDays();

        if (remDays > 0 || sb.length() == 0) {
            sb.append(remDays).append(remDays == 1 ? " día" : " días");
        }

        return sb.toString().trim();
    }

}
