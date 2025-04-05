package com.core.infrastructure.repository.appointment;

import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.appointment.IAppointmentRepository;
import com.core.infrastructure.entity.AppointmentEntity;
import com.core.infrastructure.mapper.AppointmentPersistenceMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Primary
public class AppointmentRepositoryImpl implements IAppointmentRepository {

    @Autowired
    private AppointmentCrudRepository appointmentRepository;

    @Override
    public Optional<Appointment> findById(UUID id) {
        Optional<AppointmentEntity> appointmentEntity = appointmentRepository.findById(id);
        return appointmentEntity.map(AppointmentPersistenceMapper::toDomainModel);
    }

    @Override
    public List<Appointment> findByClientId(UUID clientId) {
        List<AppointmentEntity> appointmentEntities = appointmentRepository.findByClientId(clientId);
        return appointmentEntities.stream()
                .map(AppointmentPersistenceMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Appointment update(Appointment appointment) {
        AppointmentEntity entity = AppointmentPersistenceMapper.toEntity(appointment);
        AppointmentEntity savedEntity = appointmentRepository.save(entity);
        return AppointmentPersistenceMapper.toDomainModel(savedEntity);
    }

    @Override
    public void deleteById(UUID id) {
        if (!appointmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Appointment not found with ID: " + id);
        }
        appointmentRepository.deleteById(id);
    }
}
