package com.core.testingmodule.infrastructure.appointment;

import com.core.domain.models.appointment.Appointment;
import com.core.domain.shared.DateValue;
import com.core.infrastructure.entity.AppointmentEntity;
import com.core.infrastructure.mapper.AppointmentPersistenceMapper;
import com.core.infrastructure.repository.appointment.AppointmentCrudRepository;
import com.core.infrastructure.repository.appointment.AppointmentRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentRepositoryImplTest {

    @Mock
    private AppointmentCrudRepository appointmentRepository;

    @InjectMocks
    private AppointmentRepositoryImpl appointmentRepositoryImpl;

    private UUID clientId;
    private AppointmentEntity appointmentEntity;
    private Appointment appointment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        DateValue dateValue = new DateValue(LocalDate.now());
        clientId = UUID.randomUUID();

        appointment = new Appointment(clientId, dateValue);
        appointmentEntity = new AppointmentEntity();
        appointmentEntity.setId(appointment.getId());
        appointmentEntity.setClientId(clientId);
        appointmentEntity.setDate(appointment.getDate().toLocalDate().toString());
        appointmentEntity.setStatus(appointment.getStatus());
        appointmentEntity.setAnalysisRequests(new ArrayList<>());
    }

    @Test
    void testFindById_Found() {
        when(appointmentRepository.findById(appointment.getId())).thenReturn(Optional.of(appointmentEntity));

        try (MockedStatic<AppointmentPersistenceMapper> mockedMapper = mockStatic(AppointmentPersistenceMapper.class)) {
            mockedMapper.when(() -> AppointmentPersistenceMapper.toDomainModel(appointmentEntity)).thenReturn(appointment);

            Optional<Appointment> result = appointmentRepositoryImpl.findById(appointment.getId());

            assertThat(result).isPresent();
            assertThat(result.get().getId()).isEqualTo(appointment.getId());
            verify(appointmentRepository, times(1)).findById(appointment.getId());
        }
    }

    @Test
    void testFindById_NotFound() {
        when(appointmentRepository.findById(appointment.getId())).thenReturn(Optional.empty());

        Optional<Appointment> result = appointmentRepositoryImpl.findById(appointment.getId());

        assertThat(result).isEmpty();
        verify(appointmentRepository, times(1)).findById(appointment.getId());
    }

    @Test
    void testFindByClientId() {
        when(appointmentRepository.findByClientId(clientId)).thenReturn(List.of(appointmentEntity));

        try (MockedStatic<AppointmentPersistenceMapper> mockedMapper = mockStatic(AppointmentPersistenceMapper.class)) {
            mockedMapper.when(() -> AppointmentPersistenceMapper.toDomainModel(appointmentEntity)).thenReturn(appointment);

            List<Appointment> results = appointmentRepositoryImpl.findByClientId(clientId);

            assertThat(results).hasSize(1);
            assertThat(results.get(0).getId()).isEqualTo(appointment.getId());
            verify(appointmentRepository, times(1)).findByClientId(clientId);
        }
    }

    @Test
    void testUpdate() {
        try (MockedStatic<AppointmentPersistenceMapper> mockedMapper = mockStatic(AppointmentPersistenceMapper.class)) {
            mockedMapper.when(() -> AppointmentPersistenceMapper.toEntity(appointment)).thenReturn(appointmentEntity);
            mockedMapper.when(() -> AppointmentPersistenceMapper.toDomainModel(appointmentEntity)).thenReturn(appointment);

            when(appointmentRepository.save(appointmentEntity)).thenReturn(appointmentEntity);

            Appointment result = appointmentRepositoryImpl.update(appointment);

            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(appointment.getId());
            verify(appointmentRepository, times(1)).save(appointmentEntity);
        }
    }

    @Test
    void testDeleteById_Success() {
        when(appointmentRepository.existsById(appointment.getId())).thenReturn(true);

        appointmentRepositoryImpl.deleteById(appointment.getId());

        verify(appointmentRepository, times(1)).existsById(appointment.getId());
        verify(appointmentRepository, times(1)).deleteById(appointment.getId());
    }

    @Test
    void testDeleteById_NotFound() {
        when(appointmentRepository.existsById(appointment.getId())).thenReturn(false);

        assertThatThrownBy(() -> appointmentRepositoryImpl.deleteById(appointment.getId()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Appointment not found with ID: " + appointment.getId());

        verify(appointmentRepository, times(1)).existsById(appointment.getId());
        verify(appointmentRepository, never()).deleteById(any());
    }
}
