package com.core.application.appointment.getAppointments;

import an.awesome.pipelinr.Command;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.appointment.IAppointmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAppointmentsHandler implements Command.Handler<GetAppointmentsQuery, List<Appointment>> {

    private final IAppointmentRepository appointmentRepository;

    public GetAppointmentsHandler(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> handle(GetAppointmentsQuery query) {
        return appointmentRepository.findByNutritionistId(query.nutritionistId);
    }
}
