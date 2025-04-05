package com.core.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@SuperBuilder
public class NutritionalPlanEntity {
    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private UUID clientId;

    @Column(nullable = false)
    private UUID nutritionistId;

    @ElementCollection
    @CollectionTable(
            name = "nutritional_plan_analysis_results",
            joinColumns = @JoinColumn(name = "nutritional_plan_id")
    )
    @Column(name = "analysis_result_id")
    private List<UUID> analysisResults;

    @Column(nullable = false)
    private boolean isDelivered;

    @Column(length = 5000)
    private String planDetails;
}
