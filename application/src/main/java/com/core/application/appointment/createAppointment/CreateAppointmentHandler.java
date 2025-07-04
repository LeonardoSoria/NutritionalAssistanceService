package com.core.application.appointment.createAppointment;

import an.awesome.pipelinr.Command;
import com.core.application.outbox.OutboxService;
import com.core.domain.abstracts.DomainEvent;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.appointment.AppointmentFactory;
import com.core.domain.models.appointment.IAppointmentFactory;
import com.core.domain.models.appointment.IAppointmentRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateAppointmentHandler implements Command.Handler<CreateAppointmentCommand, Appointment> {

	private final IAppointmentFactory appointmentFactory;
	private final IAppointmentRepository appointmentRepository;
	private final OutboxService outboxService;

	public CreateAppointmentHandler(
		IAppointmentRepository appointmentRepository,
		OutboxService outboxService
	) {
		this.appointmentFactory = new AppointmentFactory();
		this.appointmentRepository = appointmentRepository;
		this.outboxService = outboxService;
	}

	@Override
	public Appointment handle(CreateAppointmentCommand command) {
		Appointment appointment = appointmentFactory.create(command.clientId, command.nutritionistId, command.appointmentDate);
		appointment.scheduled();

		appointmentRepository.update(appointment);

		for (DomainEvent event : appointment.getDomainEvents()) {
			outboxService.recordEvent(event.getEventType(), event);
		}

		appointment.clearDomainEvents();
		return appointment;
	}
}
