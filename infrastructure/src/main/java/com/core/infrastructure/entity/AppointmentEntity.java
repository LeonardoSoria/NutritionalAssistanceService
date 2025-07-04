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
public class AppointmentEntity {
    @Id
    @Column(nullable = false)
    private UUID id;

    private UUID clientId;

	private UUID nutritionistId;

    private String date;

    private String status;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnalysisRequestEntity> analysisRequests;
}
