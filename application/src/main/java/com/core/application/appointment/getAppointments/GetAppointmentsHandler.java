package com.core.application.appointment.getAppointments;

import an.awesome.pipelinr.Command;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.appointment.IAppointmentRepository;
import com.core.domain.models.user.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import an.awesome.pipelinr.Command;
import com.core.application.appointment.getAppointments.dto.AppointmentDto;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.models.appointment.IAppointmentRepository;
import com.core.domain.models.user.IUserRepository;
import com.core.domain.models.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GetAppointmentsHandler implements Command.Handler<GetAppointmentsQuery, List<AppointmentDto>> {

	private final IAppointmentRepository appointmentRepository;
	private final IUserRepository userRepository;

	public GetAppointmentsHandler(IAppointmentRepository appointmentRepository, IUserRepository userRepository) {
		this.appointmentRepository = appointmentRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<AppointmentDto> handle(GetAppointmentsQuery query) {
		List<Appointment> appointments = appointmentRepository.findByNutritionistId(query.nutritionistId);

		return appointments.stream().map(app -> {
			Optional<User> client = userRepository.findById(app.getClientId());
			Optional<User> nutritionist = userRepository.findById(app.getNutritionistId());

			AppointmentDto dto = new AppointmentDto();
			dto.setId(app.getId().toString());
			dto.setClientId(app.getClientId().toString());
			dto.setClientName(client.map(User::getFullName).orElse(null));
			dto.setNutritionistId(app.getNutritionistId().toString());
			dto.setNutritionistName(nutritionist.map(User::getFullName).orElse(null));
			dto.setDate(app.getDate().toLocalDate());
			dto.setStatus(app.getStatus());
			dto.setAnalysisRequestResponses(app.getAnalysisRequests());
			return dto;
		}).collect(Collectors.toList());
	}
}
