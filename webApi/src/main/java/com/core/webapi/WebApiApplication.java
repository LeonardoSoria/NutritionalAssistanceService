package com.core.webapi;

import com.core.domain.annotations.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(
        basePackages = {
                "com.core.webapi.controller",
                "com.core.infrastructure.repository",
                "com.core.infrastructure.auth",
                "com.core.application",
                "com.core.domain.models",
                "com.core.domain.abstracts"
        }
)
@EntityScan("com.core.infrastructure.entity")
@EnableJpaRepositories(basePackages = { "com.core.infrastructure.repository" })
@EnableTransactionManagement
@EnableScheduling
@Generated
public class WebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApiApplication.class, args);
    }

}
