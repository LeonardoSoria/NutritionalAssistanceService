package com.core.webapi.controller;

import an.awesome.pipelinr.Pipeline;
import com.core.application.appointment.addAnalysisRequest.AddAnalysisRequestCommand;
import com.core.application.appointment.createAppointment.CreateAppointmentCommand;
import com.core.application.appointment.getAppointments.GetAppointmentsQuery;
import com.core.application.appointment.getAppointments.dto.AppointmentDto;
import com.core.domain.models.appointment.Appointment;
import com.core.domain.shared.DateValue;
import com.core.webapi.dto.request.AddAnalysisRequestRequest;
import com.core.webapi.dto.request.CreateAppointmentRequest;
import com.core.webapi.dto.response.AppointmentResponse;
import com.core.webapi.mapper.AppointmentMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    final Pipeline pipeline;

    public AppointmentController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentResponse> createAppointment(
            @RequestBody @Valid CreateAppointmentRequest createAppointmentRequest) {
        CreateAppointmentCommand createAppointmentCommand = new CreateAppointmentCommand(
                UUID.fromString(createAppointmentRequest.getClientId()),
                UUID.fromString(createAppointmentRequest.getNutritionistId()),
                DateValue.from(createAppointmentRequest.getAppointmentDate())
        );
        Appointment createdAppointment = createAppointmentCommand.execute(pipeline);
        AppointmentResponse response = AppointmentMapper.mapToAppointmentResponse(createdAppointment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/addRequest")
    public ResponseEntity<AppointmentResponse> addAnalysisRequest(
            @RequestBody @Valid AddAnalysisRequestRequest addAnalysisRequestRequest) {
        AddAnalysisRequestCommand addAnalysisRequestCommand = new AddAnalysisRequestCommand(
                UUID.fromString(addAnalysisRequestRequest.getAppointmentId()),
                DateValue.from(addAnalysisRequestRequest.getRequestedDate())
        );
        Appointment updatedAppointment = addAnalysisRequestCommand.execute(pipeline);
        AppointmentResponse response = AppointmentMapper.mapToAppointmentResponse(updatedAppointment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/nutritionist/{nutritionistId}")
    public ResponseEntity<List<AppointmentResponse>> createAppointment(@PathVariable String nutritionistId) {
        GetAppointmentsQuery getAppointmentsQuery = new GetAppointmentsQuery(UUID.fromString(nutritionistId));
        List<AppointmentDto> appointments = getAppointmentsQuery.execute(pipeline);
        List<AppointmentResponse> responseList = AppointmentMapper.mapToAppointmentResponseList(appointments);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

}
