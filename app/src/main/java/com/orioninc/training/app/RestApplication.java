package com.orioninc.training.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.orioninc.training"})
@EnableJpaRepositories(basePackages = {"com.orioninc.training"})
@ConfigurationPropertiesScan({"com.orioninc.training"})
@EntityScan(basePackages = {"com.orioninc.training.model"})
public class RestApplication {
    private static final Logger LOG = LoggerFactory.getLogger(RestApplication.class);
    private static ConfigurableApplicationContext context;
    public static void main(String[] args) {
        context = SpringApplication.run(RestApplication.class, args);
    }


}
