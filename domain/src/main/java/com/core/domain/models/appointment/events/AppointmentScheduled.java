package com.core.domain.models.appointment.events;

import com.core.domain.abstracts.DomainEvent;
import com.core.domain.models.appointment.AnalysisRequest;
import com.core.domain.shared.DateValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AppointmentScheduled extends DomainEvent {

    private final UUID id;
    private final UUID clientId;
	private final LocalDate date;
	private final String status;
	private final List<AnalysisRequest> analysisRequests;

    public AppointmentScheduled(UUID appointmentId, UUID clientId, DateValue date, String status) {
        this.id = appointmentId;
		this.clientId = clientId;
		this.date = date.toLocalDate();
		this.status = status;
		this.analysisRequests = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

	public UUID getClientId() {
		return clientId;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getStatus() {
		return status;
	}

	public List<AnalysisRequest> getAnalysisRequests() {
		return analysisRequests;
	}
}
