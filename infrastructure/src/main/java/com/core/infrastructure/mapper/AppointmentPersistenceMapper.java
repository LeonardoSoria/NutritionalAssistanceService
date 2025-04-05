package com.core.infrastructure.mapper;

import com.core.domain.models.appointment.AnalysisRequest;
import com.core.domain.models.appointment.AnalysisResult;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.shared.DateValue;
import com.core.infrastructure.entity.AnalysisRequestEntity;
import com.core.infrastructure.entity.AnalysisResultEntity;
import com.core.infrastructure.entity.AppointmentEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentPersistenceMapper {

    public static Appointment toDomainModel(AppointmentEntity entity) {
        // Create the Appointment object
        Appointment appointment = new Appointment(
                entity.getId(),
                entity.getClientId(),
                DateValue.from(LocalDate.parse(entity.getDate())),
                entity.getStatus(),
                Collections.emptyList()
        );

        Appointment finalAppointment = appointment;
        List<AnalysisRequest> analysisRequests = entity.getAnalysisRequests().stream()
                .map(analysisRequestEntity -> {
                    DateValue requestedDate = new DateValue(LocalDate.parse(analysisRequestEntity.getRequestedDate()));
                    return new AnalysisRequest(
                            analysisRequestEntity.getId(),
                            finalAppointment, // Pass the appointment here
                            requestedDate,
                            analysisRequestEntity.getStatus(),
                            analysisRequestEntity.getAnalysisResult() != null
                                    ? mapToAnalysisResultModel(analysisRequestEntity.getAnalysisResult())
                                    : null
                    );
                })
                .collect(Collectors.toList());

        // Now that the AnalysisRequests are correctly populated, update the Appointment object
        appointment = new Appointment(
                entity.getId(),
                entity.getClientId(),
                DateValue.from(LocalDate.parse(entity.getDate())),
                entity.getStatus(),
                analysisRequests
        );

        return appointment;
    }

    private static AnalysisResult mapToAnalysisResultModel(AnalysisResultEntity analysisResult) {
        return new AnalysisResult(
                analysisResult.getId(),
                null, // Assuming this is nullable in your domain model
                analysisResult.getResultsData(),
                DateValue.from(LocalDate.parse(analysisResult.getReceivedDate()))
        );
    }


    public static AppointmentEntity toEntity(Appointment domain) {
        // Convert DateValue to String for the date field
        String date = domain.getDate().toLocalDate().toString(); // Assuming DateValue has a toString method

        // Convert analysisRequests to entity model equivalents
        List<AnalysisRequestEntity> analysisRequestEntities = domain.getAnalysisRequests().stream()
                .map(analysisRequest -> {
                    // Map the AnalysisRequest from domain model to entity model
                    return new AnalysisRequestEntity(
                            analysisRequest.getId(),
                            null, // The entity will reference AppointmentEntity, not the domain model
                            analysisRequest.getRequestedDate().toLocalDate().toString(),
                            analysisRequest.getStatus(),
                            analysisRequest.getAnalysisResult() != null
                                    ? mapToAnalysisResultEntity(analysisRequest.getAnalysisResult())
                                    : null
                    );
                })
                .collect(Collectors.toList());

        // Create AppointmentEntity using its constructor
        AppointmentEntity entity = new AppointmentEntity(
                domain.getId(),
                domain.getClientId(),
                date,
                domain.getStatus(),
                analysisRequestEntities
        );

        analysisRequestEntities.forEach(analysisRequestEntity -> {
            analysisRequestEntity.setAppointment(entity);
            if (analysisRequestEntity.getAnalysisResult() != null
                    && analysisRequestEntity.getAnalysisResult().getAnalysisRequest() != null) {
                analysisRequestEntity.getAnalysisResult().setAnalysisRequest(analysisRequestEntity);
            }
        });

        return entity;
    }

    private static AnalysisResultEntity mapToAnalysisResultEntity(AnalysisResult analysisResult) {
        return new AnalysisResultEntity(
                analysisResult.getId(),
                null, // Assuming this is nullable in your domain model
                analysisResult.getResultsData(),
                analysisResult.getReceivedDate().toLocalDate().toString()
        );
    }
}
