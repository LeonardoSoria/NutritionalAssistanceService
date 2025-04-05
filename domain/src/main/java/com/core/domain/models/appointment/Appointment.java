package com.core.domain.models.appointment;

import com.core.domain.abstracts.AggregateRoot;
import com.core.domain.models.appointment.events.AppointmentCompleted;
import com.core.domain.shared.DateValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Appointment extends AggregateRoot {

    private final UUID id;
    private final UUID clientId;
    private final DateValue date;
    private String status;

    private final List<AnalysisRequest> analysisRequests;

    public Appointment(UUID clientId, DateValue date) {
        this.id = UUID.randomUUID();
        this.clientId = clientId;
        this.date = date;
        this.status = "Scheduled";
        this.analysisRequests = new ArrayList<>();
    }

    public Appointment(UUID id, UUID clientId, DateValue date, String status, List<AnalysisRequest> analysisRequests) {
        this.id = id;
        this.clientId = clientId;
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
     * Placeholder for publishing logic. Replace this with actual event bus logic in a real-world system.
     */
    private void publishEvent(AppointmentCompleted event) {
        System.out.println("Publishing event: " + event);
    }

    // Empty constructor for serialization in Hibernate
    private Appointment() {
        this.id = null;
        this.clientId = null;
        this.date = null;
        this.status = null;
        this.analysisRequests = null;
    }
}
