package com.core.testingmodule.infrastructure.appointment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.core.infrastructure.entity.AppointmentEntity;
import com.core.infrastructure.entity.AnalysisRequestEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class AppointmentEntityTest {

    private AppointmentEntity appointmentEntity;
    private UUID id = UUID.randomUUID();
    private UUID clientId = UUID.randomUUID();
    private String date = "2025-02-20";
    private String status = "Scheduled";
    private List<AnalysisRequestEntity> analysisRequests;

    @BeforeEach
    void setUp() {
        // Mock a list of AnalysisRequestEntities
        AnalysisRequestEntity requestEntity1 = mock(AnalysisRequestEntity.class);
        AnalysisRequestEntity requestEntity2 = mock(AnalysisRequestEntity.class);
        analysisRequests = new ArrayList<>(Arrays.asList(requestEntity1, requestEntity2));

        // Create an instance of AppointmentEntity
        appointmentEntity = AppointmentEntity.builder()
                .id(id)
                .clientId(clientId)
                .date(date)
                .status(status)
                .analysisRequests(analysisRequests)
                .build();
    }

    @Test
    void testNoArgsConstructor() {
        AppointmentEntity emptyEntity = new AppointmentEntity();
        assertNotNull(emptyEntity);
    }

    @Test
    void testAllArgsConstructor() {
        AppointmentEntity entity = new AppointmentEntity(id, clientId, date, status, analysisRequests);
        assertEquals(id, entity.getId());
        assertEquals(clientId, entity.getClientId());
        assertEquals(date, entity.getDate());
        assertEquals(status, entity.getStatus());
        assertEquals(analysisRequests, entity.getAnalysisRequests());
    }

    @Test
    void testSetters() {
        AppointmentEntity entity = new AppointmentEntity();
        UUID newId = UUID.randomUUID();
        UUID newClientId = UUID.randomUUID();
        String newDate = "2025-02-25";
        String newStatus = "Completed";
        List<AnalysisRequestEntity> newRequests = new ArrayList<>();

        entity.setId(newId);
        entity.setClientId(newClientId);
        entity.setDate(newDate);
        entity.setStatus(newStatus);
        entity.setAnalysisRequests(newRequests);

        assertEquals(newId, entity.getId());
        assertEquals(newClientId, entity.getClientId());
        assertEquals(newDate, entity.getDate());
        assertEquals(newStatus, entity.getStatus());
        assertEquals(newRequests, entity.getAnalysisRequests());
    }

    @Test
    void testEqualsAndHashCode() {
        AppointmentEntity entity1 = new AppointmentEntity(id, clientId, date, status, analysisRequests);
        AppointmentEntity entity2 = new AppointmentEntity(id, clientId, date, status, analysisRequests);
        AppointmentEntity entity3 = new AppointmentEntity(UUID.randomUUID(), clientId, date, status, analysisRequests);

        assertEquals(entity1, entity2);
        assertNotEquals(entity1, entity3);
        assertEquals(entity1.hashCode(), entity2.hashCode());
        assertNotEquals(entity1.hashCode(), entity3.hashCode());
    }

    @Test
    void testToString() {
        String toStringOutput = appointmentEntity.toString();
        assertTrue(toStringOutput.contains("AppointmentEntity"));
        assertTrue(toStringOutput.contains(id.toString()));
        assertTrue(toStringOutput.contains(clientId.toString()));
        assertTrue(toStringOutput.contains(date));
        assertTrue(toStringOutput.contains(status));
    }

    @Test
    void testSetAnalysisRequests() {
        List<AnalysisRequestEntity> newRequests = new ArrayList<>();
        appointmentEntity.setAnalysisRequests(newRequests);
        assertEquals(newRequests, appointmentEntity.getAnalysisRequests());
    }

    @Test
    void testEmptyAnalysisRequests() {
        AppointmentEntity emptyEntity = AppointmentEntity.builder()
                .id(id)
                .clientId(clientId)
                .date(date)
                .status(status)
                .analysisRequests(Collections.emptyList())
                .build();
        assertNotNull(emptyEntity.getAnalysisRequests());
        assertTrue(emptyEntity.getAnalysisRequests().isEmpty());
    }

    @Test
    void testAddAnalysisRequest() {
        AnalysisRequestEntity newRequest = mock(AnalysisRequestEntity.class);
        appointmentEntity.getAnalysisRequests().add(newRequest);
        assertTrue(appointmentEntity.getAnalysisRequests().contains(newRequest));
    }

    @Test
    void testRemoveAnalysisRequest() {
        AnalysisRequestEntity requestToRemove = appointmentEntity.getAnalysisRequests().get(0);
        appointmentEntity.getAnalysisRequests().remove(requestToRemove);
        assertFalse(appointmentEntity.getAnalysisRequests().contains(requestToRemove));
    }
}
