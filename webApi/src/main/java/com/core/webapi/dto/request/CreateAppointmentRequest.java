package com.core.webapi.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAppointmentRequest {
    private String clientId;
    private String nutritionistId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;
}
