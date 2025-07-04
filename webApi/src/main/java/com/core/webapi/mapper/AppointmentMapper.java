package com.core.webapi.mapper;

import com.core.domain.models.appointment.AnalysisRequest;
import com.core.domain.models.appointment.AnalysisResult;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.shared.DateValue;
import com.core.webapi.dto.response.AnalysisRequestResponse;
import com.core.webapi.dto.response.AnalysisResultResponse;
import com.core.webapi.dto.response.AppointmentResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentMapper {

    public static AppointmentResponse mapToAppointmentResponse(Appointment appointment) {
        if (appointment == null) {
            return null;
        }

        return AppointmentResponse.builder()
                .id(appointment.getId().toString())
                .clientId(appointment.getClientId().toString())
                .nutritionistId(appointment.getNutritionistId().toString())
                .date(convertToLocalDate(appointment.getDate()))
                .status(appointment.getStatus())
                .analysisRequestResponses(
                        appointment.getAnalysisRequests() != null
                                ? mapToAnalysisRequestResponseList(appointment.getAnalysisRequests())
                                : null)
                .build();
    }

    public static List<AppointmentResponse> mapToAppointmentResponseList(List<Appointment> appointments) {
        return appointments.stream()
                .map(AppointmentMapper::mapToAppointmentResponse)
                .collect(Collectors.toList());
    }

    private static LocalDate convertToLocalDate(DateValue dateValue) {
        return dateValue != null ? dateValue.toLocalDate() : null;
    }

    private static List<AnalysisRequestResponse> mapToAnalysisRequestResponseList(List<AnalysisRequest> analysisRequests) {
        return analysisRequests.stream()
                .map(AppointmentMapper::mapToAnalysisRequestResponse)
                .collect(Collectors.toList());
    }

    private static AnalysisRequestResponse mapToAnalysisRequestResponse(AnalysisRequest analysisRequest) {
        return AnalysisRequestResponse.builder()
                .id(analysisRequest.getId())
                .requestedDate(analysisRequest.getRequestedDate())
                .status(analysisRequest.getStatus())
                .analysisResult(analysisRequest.getAnalysisResult() != null
                        ? mapToAnalysisResultResponseList(analysisRequest.getAnalysisResult())
                        : null)
                .build();
    }

    private static AnalysisResultResponse mapToAnalysisResultResponseList(AnalysisResult analysisResults) {
        return AnalysisResultResponse.builder()
                .id(analysisResults.getId().toString())
                .receivedDate(analysisResults.getReceivedDate().toLocalDate().toString())
                .resultsData(analysisResults.getResultsData())
                .build();
    }
}
