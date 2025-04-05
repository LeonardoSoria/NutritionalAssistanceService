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
public class AnalysisRequestEntity {
    @Id
    @Column(nullable = false)
    private UUID id;

    @ManyToOne
    private AppointmentEntity appointment;

    private String requestedDate;

    private String status;

    @OneToOne(mappedBy = "analysisRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private AnalysisResultEntity analysisResult;
}
