package com.core.application.appointment.getAppointments;

import an.awesome.pipelinr.Command;
import com.core.domain.annotations.Generated;
import com.core.domain.models.appointment.Appointment;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Generated
@Getter
public class GetAppointmentsQuery implements Command<List<Appointment>> {

    UUID nutritionistId;

    public GetAppointmentsQuery(UUID nutritionistId) {
        this.nutritionistId = nutritionistId;
    }
}
