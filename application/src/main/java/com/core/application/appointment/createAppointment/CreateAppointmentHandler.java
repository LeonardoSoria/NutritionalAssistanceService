package com.core.application.appointment.createAppointment;

import an.awesome.pipelinr.Command;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.appointment.AppointmentFactory;
import com.core.domain.models.appointment.IAppointmentFactory;
import com.core.domain.models.appointment.IAppointmentRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateAppointmentHandler implements Command.Handler<CreateAppointmentCommand, Appointment> {

    private final IAppointmentFactory appointmentFactory;
    private final IAppointmentRepository appointmentRepository;

    public CreateAppointmentHandler(IAppointmentRepository appointmentRepository) {
        this.appointmentFactory = new AppointmentFactory();
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment handle(CreateAppointmentCommand command) {
        Appointment appointment = appointmentFactory.create(command.clientId, command.appointmentDate);

        appointmentRepository.update(appointment);

        return appointment;
    }
}
