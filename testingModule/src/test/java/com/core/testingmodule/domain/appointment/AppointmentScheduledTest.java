package com.core.testingmodule.domain.appointment;

import com.core.domain.models.appointment.AnalysisRequest;
import com.core.domain.models.appointment.events.AppointmentScheduled;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class AppointmentScheduledTest {

    @Test
    void testAppointmentScheduledEvent() {
        UUID appointmentId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        DateValue dateValue = DateValue.from(LocalDate.of(2025, 7, 4));
        String status = "Scheduled";

        AppointmentScheduled event = new AppointmentScheduled(appointmentId, clientId, dateValue, status);

        assertThat(event.getId()).isEqualTo(appointmentId);
        assertThat(event.getClientId()).isEqualTo(clientId);
        assertThat(event.getDate()).isEqualTo(dateValue.toLocalDate());
        assertThat(event.getStatus()).isEqualTo(status);
        assertThat(event.getAnalysisRequests()).isNotNull().isEmpty();
        assertThat(event.getEventType()).isEqualTo("APPOINTMENT_CREATED");
    }
}
