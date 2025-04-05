package com.core.webapi.dto.response;

import com.core.domain.shared.DateValue;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisRequestResponse {
    private UUID id;
    private DateValue requestedDate;
    private String status;
    private AnalysisResultResponse analysisResult;
}
