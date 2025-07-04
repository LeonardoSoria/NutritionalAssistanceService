package com.core.testingmodule.application.appointment.getAppointments;

import com.core.application.appointment.getAppointments.GetAppointmentsQuery;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetAppointmentsQueryTest {

    @Test
    void testGetAppointmentsQueryConstructor() {
        // Arrange
        UUID nutritionistId = UUID.randomUUID();

        // Act
        GetAppointmentsQuery query = new GetAppointmentsQuery(nutritionistId);

        // Assert
        assertEquals(nutritionistId, query.getNutritionistId());
    }
}
