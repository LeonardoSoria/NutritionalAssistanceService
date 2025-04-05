package com.core.webapi.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNutritionalPlanRequest {
    private String clientId;
    private String nutritionistId;
    private String planDetails;
}
