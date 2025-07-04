package com.core.testingmodule.application.outbox;

import com.core.application.outbox.mapper.AppointmentScheduledPayloadMapper;
import com.core.domain.models.appointment.AnalysisRequest;
import com.core.domain.models.appointment.events.AppointmentScheduled;
import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentScheduledPayloadMapperTest {

    private AppointmentScheduledPayloadMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new AppointmentScheduledPayloadMapper();
    }

    @Test
    void supports_returnsTrueForAppointmentScheduled() {
        AppointmentScheduled event = new AppointmentScheduled(
            UUID.randomUUID(),
            UUID.randomUUID(),
            DateValue.from(LocalDate.now()),
            "scheduled"
        );
        assertTrue(mapper.supports(event));
    }

    @Test
    void supports_returnsFalseForOtherTypes() {
        String notAnEvent = "Not an event";
        assertFalse(mapper.supports(notAnEvent));
        assertFalse(mapper.supports(null));
    }

    @Test
    void toPayload_mapsFieldsCorrectly() {
        UUID id = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        LocalDate date = LocalDate.of(2025, 7, 4);
        String status = "scheduled";

        AppointmentScheduled event = new AppointmentScheduled(
            id,
            clientId,
            DateValue.from(date),
            status
        );

        // Add dummy analysis requests if needed, but here it's empty by default
        Map<String, Object> payload = mapper.toPayload(event);

        assertEquals(id, payload.get("id"));
        assertEquals(clientId, payload.get("clientId"));
        assertEquals(date, payload.get("date"));
        assertEquals(status, payload.get("status"));
        assertNotNull(payload.get("analysisRequests"));
        assertTrue(payload.get("analysisRequests") instanceof List);
    }
}
