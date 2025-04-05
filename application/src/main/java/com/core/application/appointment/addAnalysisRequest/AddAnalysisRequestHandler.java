package com.core.application.appointment.addAnalysisRequest;

import an.awesome.pipelinr.Command;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.appointment.IAppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AddAnalysisRequestHandler implements Command.Handler<AddAnalysisRequestCommand, Appointment> {

    private final IAppointmentRepository appointmentRepository;

    public AddAnalysisRequestHandler(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment handle(AddAnalysisRequestCommand command) {
        Appointment appointment = appointmentRepository.findById(command.appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with ID: " + command.appointmentId));

        appointment.addAnalysisRequest(command.requestedDate);
        appointmentRepository.update(appointment);

        return appointment;
    }
}
