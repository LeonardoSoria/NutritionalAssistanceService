package com.core.testingmodule.domain.appointment;

import com.core.domain.models.appointment.AnalysisRequest;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AnalysisRequestTest {

    private Appointment appointment;
    private DateValue requestedDate;
    private AnalysisRequest analysisRequest;

    @BeforeEach
    void setUp() {
        // Create a valid Appointment and DateValue for setup
        UUID clientId = UUID.randomUUID();
        requestedDate = new DateValue(LocalDate.of(2025, 2, 19));
        appointment = new Appointment(clientId, requestedDate);

        // Create an AnalysisRequest
        analysisRequest = new AnalysisRequest(appointment, requestedDate);
    }

    @Test
    void testConstructor_ShouldCreateAnalysisRequest() {
        // Assert that the constructor creates an AnalysisRequest with correct values
        assertNotNull(analysisRequest);
        assertNotNull(analysisRequest.getId()); // ID should be auto-generated
        assertEquals(appointment, analysisRequest.getAppointment());
        assertEquals(requestedDate, analysisRequest.getRequestedDate());
        assertEquals("Pending", analysisRequest.getStatus()); // Default status
        assertNull(analysisRequest.getAnalysisResult()); // Default value should be null
    }

    @Test
    void testSubmitRequest_ShouldChangeStatusToSubmitted() {
        // Act
        analysisRequest.submitRequest();

        // Assert
        assertEquals("Submitted", analysisRequest.getStatus());
    }

    @Test
    void testSubmitRequest_WhenNotPending_ShouldThrowException() {
        // Arrange
        analysisRequest.submitRequest();

        // Act & Assert
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> analysisRequest.submitRequest());
        assertEquals("Only pending analysis requests can be submitted.", thrown.getMessage());
    }

    @Test
    void testSetAnalysisResult_ShouldSetAnalysisResultAndMarkAsCompleted() {
        // Arrange
        analysisRequest.submitRequest(); // Submit the request first
        String resultsData = "Test result data";
        DateValue receivedDate = new DateValue(LocalDate.of(2025, 2, 19));

        // Act
        analysisRequest.setAnalysisResult(resultsData, receivedDate);

        // Assert
        assertNotNull(analysisRequest.getAnalysisResult());
        assertEquals("Completed", analysisRequest.getStatus());
        assertEquals(resultsData, analysisRequest.getAnalysisResult().getResultsData());
        assertEquals(receivedDate, analysisRequest.getAnalysisResult().getReceivedDate());
    }

    @Test
    void testSetAnalysisResult_WhenNotSubmitted_ShouldThrowException() {
        // Arrange
        String resultsData = "Test result data";
        DateValue receivedDate = new DateValue(LocalDate.of(2025, 2, 19));

        // Act & Assert
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> analysisRequest.setAnalysisResult(resultsData, receivedDate));
        assertEquals("Analysis result can only be added to a submitted analysis request.", thrown.getMessage());
    }

    @Test
    void testSetAnalysisResult_WhenAlreadyHasResult_ShouldThrowException() {
        // Arrange
        analysisRequest.submitRequest();
        String resultsData = "Test result data";
        DateValue receivedDate = new DateValue(LocalDate.of(2025, 2, 19));
        analysisRequest.setAnalysisResult(resultsData, receivedDate); // Set the first result

        // Act & Assert
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> analysisRequest.setAnalysisResult(resultsData, receivedDate));

        // This checks that the exception message matches what's expected from your code
        assertEquals("Analysis result can only be added to a submitted analysis request.", thrown.getMessage());
    }

    @Test
    void testMarkAsCompleted_ShouldChangeStatusToCompleted() {
        // Arrange
        analysisRequest.submitRequest(); // Submit the request first
        String resultsData = "Test result data";
        DateValue receivedDate = new DateValue(LocalDate.of(2025, 2, 19));
        analysisRequest.setAnalysisResult(resultsData, receivedDate);

        // Act
        analysisRequest.markAsCompleted();

        // Assert
        assertEquals("Completed", analysisRequest.getStatus());
    }

    @Test
    void testMarkAsCompleted_WhenNoAnalysisResult_ShouldThrowException() {
        // Act & Assert
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> analysisRequest.markAsCompleted());
        assertEquals("Cannot mark as completed without an analysis result.", thrown.getMessage());
    }
}
