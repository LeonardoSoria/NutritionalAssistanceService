package com.core.webapi.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private String id;
    private String clientId;
    private String nutritionistId;
    private LocalDate date;
    private String status;
    private List<AnalysisRequestResponse> analysisRequestResponses;
}
