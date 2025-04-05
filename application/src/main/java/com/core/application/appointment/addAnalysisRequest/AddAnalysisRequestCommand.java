package com.core.application.appointment.addAnalysisRequest;

import an.awesome.pipelinr.Command;
import com.core.domain.annotations.Generated;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.shared.DateValue;
import lombok.Getter;

import java.util.UUID;

@Generated
@Getter
public class AddAnalysisRequestCommand implements Command<Appointment> {

    UUID appointmentId;
    DateValue requestedDate;

    public AddAnalysisRequestCommand(UUID appointmentId, DateValue requestedDate) {
        this.appointmentId = appointmentId;
        this.requestedDate = requestedDate;
    }

}
