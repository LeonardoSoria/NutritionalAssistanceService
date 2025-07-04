package com.core.domain.models.appointment;

import com.core.domain.abstracts.AggregateRoot;
import com.core.domain.abstracts.DomainEvent;
import com.core.domain.models.appointment.events.AppointmentCompleted;
import com.core.domain.models.appointment.events.AppointmentScheduled;
import com.core.domain.shared.DateValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Appointment extends AggregateRoot {

	private final UUID id;
	private final UUID clientId;
	private final UUID nutritionistId;
	private final DateValue date;
	private String status;

	private final List<AnalysisRequest> analysisRequests;

	public Appointment(UUID clientId, UUID nutritionistId, DateValue date) {
		this.id = UUID.randomUUID();
		this.clientId = clientId;
		this.nutritionistId = nutritionistId;
		this.date = date;
		this.analysisRequests = new ArrayList<>();
	}

	public Appointment(UUID id, UUID clientId, UUID nutritionistId, DateValue date, String status, List<AnalysisRequest> analysisRequests) {
		this.id = id;
		this.clientId = clientId;
		this.nutritionistId = nutritionistId;
		this.date = date;
		this.status = status;
		this.analysisRequests = analysisRequests;
	}

	public UUID getId() {
		return id;
	}

	public UUID getClientId() {
		return clientId;
	}

	public UUID getNutritionistId() {
		return nutritionistId;
	}

	public DateValue getDate() {
		return date;
	}

	public String getStatus() {
		return status;
	}

	public List<AnalysisRequest> getAnalysisRequests() {
		return Collections.unmodifiableList(analysisRequests);
	}

	/**
	 * Schedules the appointment and changes the status to 'Scheduled'.
	 */
	public void scheduled() {
		if (status != null) {
			throw new IllegalStateException("Appointment must be in a null status to mark as scheduled.");
		}
		this.status = "Scheduled";
		System.out.println("Appointment scheduled successfully with ID: " + id);

		AppointmentScheduled event = new AppointmentScheduled(this.id, this.clientId, this.date, this.status);
		publishEvent(event);
	}

	/**
	 * Completes the appointment and changes the status to 'Completed'.
	 */
	public void complete() {
		if (!"Scheduled".equals(status)) {
			throw new IllegalStateException("Appointment must be in Scheduled status to mark as completed.");
		}
		this.status = "Completed";
		System.out.println("Appointment completed successfully with ID: " + id);

		AppointmentCompleted event = new AppointmentCompleted(this.id);
		publishEvent(event);
	}

	/**
	 * Adds a new analysis request.
	 */
	public void addAnalysisRequest(DateValue requestedDate) {
		if (!"Scheduled".equals(status)) {
			throw new IllegalStateException("Cannot add analysis requests to a non-scheduled appointment.");
		}
		this.analysisRequests.add(new AnalysisRequest(this, requestedDate));
	}

	/**
	 * Sets an AnalysisResult for a specific AnalysisRequest within this Appointment.
	 */
	public void setAnalysisResult(UUID analysisRequestId, String resultsData, DateValue receivedDate) {
		// Find the target AnalysisRequest
		AnalysisRequest request = this.analysisRequests.stream()
			.filter(ar -> ar.getId().equals(analysisRequestId))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Analysis request not found"));

		// Delegate the responsibility of setting the result to the AnalysisRequest
		request.setAnalysisResult(resultsData, receivedDate);
	}

	/**
	 * Placeholder for publishing logic
	 */
	private void publishEvent(DomainEvent event) {
		addDomainEvent(event);
	}

	// Empty constructor for serialization in Hibernate
	private Appointment() {
		this.id = null;
		this.clientId = null;
		this.nutritionistId = null;
		this.date = null;
		this.status = null;
		this.analysisRequests = null;
	}
}
