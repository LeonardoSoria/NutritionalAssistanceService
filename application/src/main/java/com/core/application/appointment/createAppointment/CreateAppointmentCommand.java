package com.core.application.appointment.createAppointment;

import an.awesome.pipelinr.Command;
import com.core.domain.annotations.Generated;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.shared.DateValue;
import lombok.Getter;

import java.util.UUID;

@Generated
@Getter
public class CreateAppointmentCommand implements Command<Appointment> {

    UUID clientId;
    UUID nutritionistId;
    DateValue appointmentDate;

    public CreateAppointmentCommand(UUID clientId, UUID nutritionistId, DateValue appointmentDate) {
        this.clientId = clientId;
        this.nutritionistId = nutritionistId;
        this.appointmentDate = appointmentDate;
    }

}
