package com.core.testingmodule.infrastructure.appointment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.core.infrastructure.entity.AnalysisResultEntity;
import com.core.infrastructure.entity.AnalysisRequestEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class AnalysisResultEntityTest {

    private AnalysisResultEntity analysisResultEntity;
    private UUID id = UUID.randomUUID();
    private String resultsData = "Test results data";
    private String receivedDate = "2025-02-20";
    private AnalysisRequestEntity analysisRequestEntity;

    @BeforeEach
    void setUp() {
        // Mock AnalysisRequestEntity
        analysisRequestEntity = mock(AnalysisRequestEntity.class);

        // Create an instance of AnalysisResultEntity with mocked AnalysisRequestEntity
        analysisResultEntity = AnalysisResultEntity.builder()
                .id(id)
                .analysisRequest(analysisRequestEntity)
                .resultsData(resultsData)
                .receivedDate(receivedDate)
                .build();
    }

    @Test
    void testGetId() {
        // Assert the ID is correctly set and retrieved
        assertEquals(id, analysisResultEntity.getId());
    }

    @Test
    void testGetAnalysisRequest() {
        // Assert the AnalysisRequestEntity is correctly set and retrieved
        assertEquals(analysisRequestEntity, analysisResultEntity.getAnalysisRequest());
    }

    @Test
    void testGetResultsData() {
        // Assert the resultsData is correctly set and retrieved
        assertEquals(resultsData, analysisResultEntity.getResultsData());
    }

    @Test
    void testGetReceivedDate() {
        // Assert the receivedDate is correctly set and retrieved
        assertEquals(receivedDate, analysisResultEntity.getReceivedDate());
    }

    @Test
    void testSetAnalysisRequest() {
        // Create a new AnalysisRequestEntity and set it
        AnalysisRequestEntity newAnalysisRequest = mock(AnalysisRequestEntity.class);
        analysisResultEntity.setAnalysisRequest(newAnalysisRequest);

        // Assert the AnalysisRequestEntity is updated correctly
        assertEquals(newAnalysisRequest, analysisResultEntity.getAnalysisRequest());
    }

    @Test
    void testSetResultsData() {
        // Create a new resultsData and set it
        String newResultsData = "Updated results data";
        analysisResultEntity.setResultsData(newResultsData);

        // Assert the resultsData is updated correctly
        assertEquals(newResultsData, analysisResultEntity.getResultsData());
    }

    @Test
    void testSetReceivedDate() {
        // Create a new receivedDate and set it
        String newReceivedDate = "2025-02-21";
        analysisResultEntity.setReceivedDate(newReceivedDate);

        // Assert the receivedDate is updated correctly
        assertEquals(newReceivedDate, analysisResultEntity.getReceivedDate());
    }

    @Test
    void testBuilderPattern() {
        // Test if the builder pattern works
        AnalysisResultEntity entity = AnalysisResultEntity.builder()
                .id(UUID.randomUUID())
                .analysisRequest(mock(AnalysisRequestEntity.class))
                .resultsData("Some result data")
                .receivedDate("2025-02-22")
                .build();

        // Assert all fields set correctly
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertNotNull(entity.getAnalysisRequest());
        assertNotNull(entity.getResultsData());
        assertNotNull(entity.getReceivedDate());
    }

    @Test
    void testEqualsAndHashCode() {
        AnalysisResultEntity sameEntity = AnalysisResultEntity.builder()
                .id(id)
                .analysisRequest(analysisRequestEntity)
                .resultsData(resultsData)
                .receivedDate(receivedDate)
                .build();

        assertEquals(analysisResultEntity, sameEntity);
        assertEquals(analysisResultEntity.hashCode(), sameEntity.hashCode());
    }

    @Test
    void testNoArgsConstructor() {
        AnalysisResultEntity defaultEntity = new AnalysisResultEntity();
        assertNotNull(defaultEntity);
    }

    @Test
    void testOneToOneMapping() {
        assertNotNull(analysisResultEntity.getAnalysisRequest());
        assertTrue(analysisResultEntity.getAnalysisRequest() instanceof AnalysisRequestEntity);
    }
}
