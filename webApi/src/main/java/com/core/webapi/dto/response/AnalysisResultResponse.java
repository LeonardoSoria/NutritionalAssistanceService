package com.core.webapi.dto.response;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisResultResponse {
    private String id;
    private String resultsData;
    private String receivedDate;
}
