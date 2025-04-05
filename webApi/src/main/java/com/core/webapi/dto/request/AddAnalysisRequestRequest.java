package com.core.webapi.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddAnalysisRequestRequest {
    private String appointmentId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate requestedDate;
}
