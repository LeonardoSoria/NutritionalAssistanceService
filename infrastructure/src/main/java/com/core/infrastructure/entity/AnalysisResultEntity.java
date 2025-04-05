package com.core.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@SuperBuilder
public class AnalysisResultEntity {
    @Id
    @Column(nullable = false)
    private UUID id;

    @OneToOne
    private AnalysisRequestEntity analysisRequest;

    private String resultsData;

    private String receivedDate;
}
