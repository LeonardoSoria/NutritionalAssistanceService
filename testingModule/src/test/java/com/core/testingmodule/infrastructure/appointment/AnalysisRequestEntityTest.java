package com.core.testingmodule.infrastructure.appointment;

import com.core.infrastructure.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

class AnalysisRequestEntityTest {

    private AnalysisRequestEntity analysisRequestEntity;
    private AppointmentEntity appointmentEntity;
    private AnalysisResultEntity analysisResultEntity;

    @BeforeEach
    void setUp() {
        appointmentEntity = new AppointmentEntity();
        appointmentEntity.setId(UUID.randomUUID());

        analysisResultEntity = new AnalysisResultEntity();
        analysisResultEntity.setId(UUID.randomUUID());

        analysisRequestEntity = new AnalysisRequestEntity(
                UUID.randomUUID(),
                appointmentEntity,
                "2025-02-21",
                "Pending",
                analysisResultEntity
        );
    }

    @Test
    void testConstructor() {
        assertNotNull(analysisRequestEntity.getId());
        assertEquals("2025-02-21", analysisRequestEntity.getRequestedDate());
        assertEquals("Pending", analysisRequestEntity.getStatus());
        assertNotNull(analysisRequestEntity.getAppointment());
        assertNotNull(analysisRequestEntity.getAnalysisResult());
    }

    @Test
    void testBuilder() {
        AnalysisRequestEntity builtEntity = AnalysisRequestEntity.builder()
                .id(UUID.randomUUID())
                .appointment(appointmentEntity)
                .requestedDate("2025-02-21")
                .status("Completed")
                .analysisResult(analysisResultEntity)
                .build();

        assertNotNull(builtEntity.getId());
        assertEquals("Completed", builtEntity.getStatus());
        assertEquals(appointmentEntity, builtEntity.getAppointment());
        assertEquals(analysisResultEntity, builtEntity.getAnalysisResult());
    }

    @Test
    void testSettersAndGetters() {
        analysisRequestEntity.setRequestedDate("2025-03-21");
        analysisRequestEntity.setStatus("Completed");

        assertEquals("2025-03-21", analysisRequestEntity.getRequestedDate());
        assertEquals("Completed", analysisRequestEntity.getStatus());
    }

    @Test
    void testEqualsAndHashCode() {
        AnalysisRequestEntity anotherEntity = new AnalysisRequestEntity(
                analysisRequestEntity.getId(),
                appointmentEntity,
                "2025-02-21",
                "Pending",
                analysisResultEntity
        );

        assertEquals(analysisRequestEntity, anotherEntity);
        assertEquals(analysisRequestEntity.hashCode(), anotherEntity.hashCode());
    }

    @Test
    void testToString() {
        String toStringResult = analysisRequestEntity.toString();
        assertNotNull(toStringResult);
        assertTrue(toStringResult.contains("AnalysisRequestEntity"));
    }
}
