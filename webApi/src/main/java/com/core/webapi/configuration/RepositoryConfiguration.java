package com.core.webapi.configuration;

import com.core.domain.models.appointment.IAppointmentRepository;
import com.core.domain.models.nutritionalPlan.INutritionalPlanRepository;
import com.core.infrastructure.repository.appointment.AppointmentRepositoryImpl;
import com.core.infrastructure.repository.nutritionalPlan.NutritionalPlanRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean(name = "iAppointmentRepository")
    public IAppointmentRepository iAppointmentRepository() {
        return new AppointmentRepositoryImpl();
    }

    @Bean(name = "iNutritionalPlanRepository")
    public INutritionalPlanRepository iNutritionalPlanRepository() {
        return new NutritionalPlanRepositoryImpl();
    }
}
