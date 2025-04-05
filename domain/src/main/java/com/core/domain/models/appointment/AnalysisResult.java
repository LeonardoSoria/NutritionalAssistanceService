package com.core.domain.models.appointment;

import com.core.domain.abstracts.Entity;
import com.core.domain.shared.DateValue;

import java.util.UUID;

public class AnalysisResult extends Entity {

    private final UUID id;
    private final AnalysisRequest analysisRequest;
    private final String resultsData;
    private final DateValue receivedDate;

    public AnalysisResult(AnalysisRequest analysisRequest, String resultsData, DateValue receivedDate) {
        this.id = UUID.randomUUID();
        this.analysisRequest = analysisRequest;
        this.resultsData = resultsData;
        this.receivedDate = receivedDate;
    }

    public AnalysisResult(UUID id, AnalysisRequest analysisRequest, String resultsData, DateValue receivedDate) {
        this.id = id;
        this.analysisRequest = analysisRequest;
        this.resultsData = resultsData;
        this.receivedDate = receivedDate;
    }

    public UUID getId() {
        return id;
    }

    public AnalysisRequest getAnalysisRequest() {
        return analysisRequest;
    }

    public String getResultsData() {
        return resultsData;
    }

    public DateValue getReceivedDate() {
        return receivedDate;
    }

    @Override
    public String toString() {
        return "AnalysisResult{" +
                "id=" + id +
                ", analysisRequest=" + analysisRequest +
                ", resultsData='" + resultsData + '\'' +
                ", receivedDate=" + receivedDate +
                '}';
    }
}
