package com.core.application.appointment.getAppointments.dto;

import com.core.domain.models.appointment.AnalysisRequest;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
	private String id;
	private String clientId;
	private String clientName;
	private String nutritionistId;
	private String nutritionistName;
	private LocalDate date;
	private String status;
	private List<AnalysisRequest> analysisRequestResponses;
}
