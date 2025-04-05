package com.core.testingmodule.domain.appointment;

import com.core.domain.models.appointment.AnalysisRequest;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentTest {

    private UUID clientId;
    private DateValue date;
    private Appointment appointment;

    @BeforeEach
    void setUp() {
        clientId = UUID.randomUUID();
        date = mock(DateValue.class);
        appointment = new Appointment(clientId, date);
    }

    @Test
    void testAppointmentInitialization() {
        assertNotNull(appointment.getId());
        assertEquals(clientId, appointment.getClientId());
        assertEquals(date, appointment.getDate());
        assertEquals("Scheduled", appointment.getStatus());
        assertTrue(appointment.getAnalysisRequests().isEmpty());
    }

    @Test
    void testCompleteAppointmentSuccessfully() {
        appointment.complete();
        assertEquals("Completed", appointment.getStatus());
    }

    @Test
    void testCompleteAppointmentInvalidState() {
        appointment.complete(); // First completion

        IllegalStateException exception = assertThrows(IllegalStateException.class, appointment::complete);
        assertEquals("Appointment must be in Scheduled status to mark as completed.", exception.getMessage());
    }

    @Test
    void testAddAnalysisRequestSuccessfully() {
        DateValue requestedDate = mock(DateValue.class);
        appointment.addAnalysisRequest(requestedDate);

        List<AnalysisRequest> analysisRequests = appointment.getAnalysisRequests();
        assertEquals(1, analysisRequests.size());
        assertEquals(requestedDate, analysisRequests.get(0).getRequestedDate());
    }

    @Test
    void testAddAnalysisRequestInvalidState() {
        appointment.complete();

        DateValue requestedDate = mock(DateValue.class);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> appointment.addAnalysisRequest(requestedDate));
        assertEquals("Cannot add analysis requests to a non-scheduled appointment.", exception.getMessage());
    }

    @Test
    void testSetAnalysisResultSuccessfully() {
        DateValue requestedDate = mock(DateValue.class);
        appointment.addAnalysisRequest(requestedDate);

        AnalysisRequest analysisRequest = appointment.getAnalysisRequests().get(0);
        analysisRequest.submitRequest();
        UUID analysisRequestId = analysisRequest.getId();
        String resultsData = "Sample Results";
        DateValue receivedDate = mock(DateValue.class);

        appointment.setAnalysisResult(analysisRequestId, resultsData, receivedDate);

        assertEquals(resultsData, analysisRequest.getAnalysisResult().getResultsData());
        assertEquals(receivedDate, analysisRequest.getAnalysisResult().getReceivedDate());
    }

    @Test
    void testSetAnalysisResultInvalidRequest() {
        UUID invalidRequestId = UUID.randomUUID();
        String resultsData = "Invalid Results";
        DateValue receivedDate = mock(DateValue.class);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> appointment.setAnalysisResult(invalidRequestId, resultsData, receivedDate));
        assertEquals("Analysis request not found", exception.getMessage());
    }
}
