package com.core.testingmodule.domain.appointment;

import com.core.domain.models.appointment.AnalysisRequest;
import com.core.domain.models.appointment.AnalysisResult;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AnalysisResultTest {

    private AnalysisRequest analysisRequest;
    private DateValue receivedDate;

    @BeforeEach
    void setUp() {
        UUID clientId = UUID.randomUUID();
        UUID nutritionistId = UUID.randomUUID();
        receivedDate = new DateValue(LocalDate.of(2025, 2, 19));
        Appointment appointment = new Appointment(clientId, nutritionistId, receivedDate);

        analysisRequest = new AnalysisRequest(appointment, receivedDate);
    }

    @Test
    void testConstructor_WithValidParams_ShouldCreateAnalysisResult() {
        // Arrange
        String resultsData = "Test result data";

        // Act
        AnalysisResult analysisResult = new AnalysisResult(analysisRequest, resultsData, receivedDate);

        // Assert
        assertNotNull(analysisResult);
        assertNotNull(analysisResult.getId());
        assertEquals(analysisRequest, analysisResult.getAnalysisRequest());
        assertEquals(resultsData, analysisResult.getResultsData());
        assertEquals(receivedDate, analysisResult.getReceivedDate());
    }

    @Test
    void testConstructor_WithCustomId_ShouldCreateAnalysisResult() {
        // Arrange
        UUID customId = UUID.randomUUID();
        String resultsData = "Test result data with custom ID";

        // Act
        AnalysisResult analysisResult = new AnalysisResult(customId, analysisRequest, resultsData, receivedDate);

        // Assert
        assertNotNull(analysisResult);
        assertEquals(customId, analysisResult.getId());
        assertEquals(analysisRequest, analysisResult.getAnalysisRequest());
        assertEquals(resultsData, analysisResult.getResultsData());
        assertEquals(receivedDate, analysisResult.getReceivedDate());
    }

    @Test
    void testGetters_ShouldReturnCorrectValues() {
        // Arrange
        String resultsData = "Test result data";

        // Act
        AnalysisResult analysisResult = new AnalysisResult(analysisRequest, resultsData, receivedDate);

        // Assert
        assertEquals(analysisRequest, analysisResult.getAnalysisRequest());
        assertEquals(resultsData, analysisResult.getResultsData());
        assertEquals(receivedDate, analysisResult.getReceivedDate());
    }

    @Test
    void testToString_ShouldReturnCorrectStringRepresentation() {
        // Arrange
        String resultsData = "Test result data";
        AnalysisResult analysisResult = new AnalysisResult(analysisRequest, resultsData, receivedDate);

        // Act
        String result = analysisResult.toString();

        // Assert
        assertTrue(result.contains("id=" + analysisResult.getId().toString()));
        assertTrue(result.contains("analysisRequest=" + analysisRequest.toString())); // Assuming a meaningful toString method in AnalysisRequest
        assertTrue(result.contains("resultsData='" + resultsData + "'"));
        assertTrue(result.contains("receivedDate=" + receivedDate.toString()));
    }
}
