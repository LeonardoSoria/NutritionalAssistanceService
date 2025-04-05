package com.core.domain.models.appointment;

import com.core.domain.abstracts.Entity;
import com.core.domain.shared.DateValue;

import java.util.UUID;

public class AnalysisRequest extends Entity {

    private final UUID id;
    private final Appointment appointment;
    private final DateValue requestedDate;
    private String status;
    private AnalysisResult analysisResult;

    public AnalysisRequest(Appointment appointment, DateValue requestedDate) {
        this.id = UUID.randomUUID();
        this.appointment = appointment;
        this.requestedDate = requestedDate;
        this.status = "Pending";
        this.analysisResult = null;
    }

    public AnalysisRequest(UUID id, Appointment appointment, DateValue requestedDate, String status, AnalysisResult result) {
        this.id = id;
        this.appointment = appointment;
        this.requestedDate = requestedDate;
        this.status = status;
        this.analysisResult = result;
    }

    public UUID getId() {
        return id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public DateValue getRequestedDate() {
        return requestedDate;
    }

    public String getStatus() {
        return status;
    }

    public AnalysisResult getAnalysisResult() {
        return analysisResult;
    }

    public void setAnalysisResult(String resultsData, DateValue receivedDate) {
        if (!"Submitted".equals(this.status)) {
            throw new IllegalStateException("Analysis result can only be added to a submitted analysis request.");
        }
        if (this.analysisResult != null) {
            throw new IllegalStateException("This analysis request already has a result.");
        }
        this.analysisResult = new AnalysisResult(this, resultsData, receivedDate);
        markAsCompleted();
    }

    public void markAsCompleted() {
        if (this.analysisResult == null) {
            throw new IllegalStateException("Cannot mark as completed without an analysis result.");
        }
        this.status = "Completed";
    }

    public void submitRequest() {
        if (!"Pending".equals(this.status)) {
            throw new IllegalStateException("Only pending analysis requests can be submitted.");
        }
        this.status = "Submitted";
    }
}
