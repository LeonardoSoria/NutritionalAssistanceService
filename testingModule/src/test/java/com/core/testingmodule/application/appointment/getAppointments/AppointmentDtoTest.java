package com.core.testingmodule.application.appointment.getAppointments;

import com.core.application.appointment.getAppointments.dto.AppointmentDto;
import com.core.domain.models.appointment.AnalysisRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentDtoTest {

    @Test
    void testBuilderAndGetters() {
        // Arrange
        String id = UUID.randomUUID().toString();
        String clientId = UUID.randomUUID().toString();
        String clientName = "Client Test";
        String nutritionistId = UUID.randomUUID().toString();
        String nutritionistName = "Nutritionist Test";
        LocalDate date = LocalDate.now();
        String status = "CONFIRMED";
        List<AnalysisRequest> analysisRequests = List.of();  // empty for test

        // Act
        AppointmentDto dto = AppointmentDto.builder()
                .id(id)
                .clientId(clientId)
                .clientName(clientName)
                .nutritionistId(nutritionistId)
                .nutritionistName(nutritionistName)
                .date(date)
                .status(status)
                .analysisRequestResponses(analysisRequests)
                .build();

        // Assert
        assertEquals(id, dto.getId());
        assertEquals(clientId, dto.getClientId());
        assertEquals(clientName, dto.getClientName());
        assertEquals(nutritionistId, dto.getNutritionistId());
        assertEquals(nutritionistName, dto.getNutritionistName());
        assertEquals(date, dto.getDate());
        assertEquals(status, dto.getStatus());
        assertEquals(analysisRequests, dto.getAnalysisRequestResponses());
    }

    @Test
    void testSetters() {
        // Arrange
        AppointmentDto dto = new AppointmentDto();

        String id = UUID.randomUUID().toString();
        String clientId = UUID.randomUUID().toString();
        String nutritionistId = UUID.randomUUID().toString();
        LocalDate date = LocalDate.of(2025, 1, 1);
        List<AnalysisRequest> analysisRequests = List.of();

        // Act
        dto.setId(id);
        dto.setClientId(clientId);
        dto.setClientName("Client A");
        dto.setNutritionistId(nutritionistId);
        dto.setNutritionistName("Nutritionist A");
        dto.setDate(date);
        dto.setStatus("PENDING");
        dto.setAnalysisRequestResponses(analysisRequests);

        // Assert
        assertEquals(id, dto.getId());
        assertEquals(clientId, dto.getClientId());
        assertEquals("Client A", dto.getClientName());
        assertEquals(nutritionistId, dto.getNutritionistId());
        assertEquals("Nutritionist A", dto.getNutritionistName());
        assertEquals(date, dto.getDate());
        assertEquals("PENDING", dto.getStatus());
        assertEquals(analysisRequests, dto.getAnalysisRequestResponses());
    }
}
