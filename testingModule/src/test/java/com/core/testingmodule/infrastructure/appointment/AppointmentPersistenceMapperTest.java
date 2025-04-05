package com.core.testingmodule.infrastructure.appointment;

import static org.junit.jupiter.api.Assertions.*;

import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.appointment.AnalysisRequest;
import com.core.domain.models.appointment.AnalysisResult;
import com.core.domain.shared.DateValue;
import com.core.infrastructure.entity.AppointmentEntity;
import com.core.infrastructure.entity.AnalysisRequestEntity;
import com.core.infrastructure.entity.AnalysisResultEntity;
import com.core.infrastructure.mapper.AppointmentPersistenceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

class AppointmentPersistenceMapperTest {

    private AppointmentEntity appointmentEntity;
    private Appointment domainAppointment;
    private AnalysisRequestEntity analysisRequestEntity;
    private AnalysisResultEntity analysisResultEntity;
    private AnalysisRequest domainAnalysisRequest;
    private AnalysisResult domainAnalysisResult;

    private UUID appointmentId = UUID.randomUUID();
    private UUID clientId = UUID.randomUUID();
    private UUID analysisRequestId = UUID.randomUUID();
    private UUID analysisResultId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        // Setup AppointmentEntity for testing
        appointmentEntity = new AppointmentEntity(
                appointmentId,
                clientId,
                "2025-02-20",
                "Scheduled",
                Collections.emptyList()
        );

        // Setup AnalysisRequestEntity for testing
        analysisResultEntity = new AnalysisResultEntity(analysisResultId, null, "ResultsData", "2025-02-21");
        analysisRequestEntity = new AnalysisRequestEntity(
                analysisRequestId,
                null,
                "2025-02-20",
                "Pending",
                analysisResultEntity
        );

        // Setup domain models for testing
        domainAnalysisResult = new AnalysisResult(analysisResultId, null, "ResultsData", DateValue.from(LocalDate.parse("2025-02-21")));
        domainAnalysisRequest = new AnalysisRequest(
                analysisRequestId,
                null,
                DateValue.from(LocalDate.parse("2025-02-20")),
                "Pending",
                domainAnalysisResult
        );

        domainAppointment = new Appointment(
                appointmentId,
                clientId,
                DateValue.from(LocalDate.parse("2025-02-20")),
                "Scheduled",
                Arrays.asList(domainAnalysisRequest)
        );
    }

    @Test
    void testToDomainModel() {
        // Mock AppointmentEntity's AnalysisRequest list
        appointmentEntity.setAnalysisRequests(Arrays.asList(analysisRequestEntity));

        // Perform conversion from entity to domain model
        Appointment result = AppointmentPersistenceMapper.toDomainModel(appointmentEntity);

        // Assert the conversion is correct
        assertNotNull(result);
        assertEquals(appointmentEntity.getId(), result.getId());
        assertEquals(appointmentEntity.getClientId(), result.getClientId());
        assertEquals(appointmentEntity.getDate(), result.getDate().toLocalDate().toString());
        assertEquals(appointmentEntity.getStatus(), result.getStatus());
        assertEquals(1, result.getAnalysisRequests().size());
        assertEquals(analysisRequestEntity.getId(), result.getAnalysisRequests().get(0).getId());
    }

    @Test
    void testToEntity() {
        // Perform conversion from domain model to entity
        AppointmentEntity result = AppointmentPersistenceMapper.toEntity(domainAppointment);

        // Assert the conversion is correct
        assertNotNull(result);
        assertEquals(domainAppointment.getId(), result.getId());
        assertEquals(domainAppointment.getClientId(), result.getClientId());
        assertEquals(domainAppointment.getDate().toLocalDate().toString(), result.getDate());
        assertEquals(domainAppointment.getStatus(), result.getStatus());
        assertEquals(1, result.getAnalysisRequests().size());
        assertEquals(domainAnalysisRequest.getId(), result.getAnalysisRequests().get(0).getId());
    }

    @Test
    void testToDomainModelWithNullAnalysisResult() {
        // Set AnalysisRequestEntity with null AnalysisResultEntity
        analysisRequestEntity.setAnalysisResult(null);
        appointmentEntity.setAnalysisRequests(Arrays.asList(analysisRequestEntity));

        // Perform conversion from entity to domain model
        Appointment result = AppointmentPersistenceMapper.toDomainModel(appointmentEntity);

        // Assert the conversion is correct
        assertNotNull(result);
        assertEquals(1, result.getAnalysisRequests().size());
        assertNull(result.getAnalysisRequests().get(0).getAnalysisResult());
    }
}
