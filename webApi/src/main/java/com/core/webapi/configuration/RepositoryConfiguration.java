package com.core.webapi.configuration;

import com.core.domain.models.appointment.IAppointmentRepository;
import com.core.domain.models.nutritionalPlan.INutritionalPlanRepository;
import com.core.domain.models.outbox.IOutboxRepository;
import com.core.domain.models.user.IUserRepository;
import com.core.infrastructure.repository.appointment.AppointmentRepositoryImpl;
import com.core.infrastructure.repository.nutritionalPlan.NutritionalPlanRepositoryImpl;
import com.core.infrastructure.repository.outbox.OutboxRepositoryImpl;
import com.core.infrastructure.repository.user.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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

	@Bean(name = "iOutboxRepository")
	public IOutboxRepository iOutboxRepository() {
		return new OutboxRepositoryImpl();
	}

	@Bean(name = "iUserRepository")
	public IUserRepository iUserRepository() {
		return new UserRepositoryImpl();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
