package com.orioninc.training.rest;

import com.orioninc.training.model.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.orioninc.training"})
@EnableJpaRepositories(basePackages = {"com.orioninc.training"})
//@EnableJpaRepositories(basePackages = {"com.orioninc.training.model_api","com.orioninc.training.model"})
@ConfigurationPropertiesScan({"com.orioninc.training"})
@EntityScan(basePackages = {"com.orioninc.training.model", "com.orioninc.training.model_api"})
//@EntityScan(basePackages = {"com.orioninc.training.*"})

public class RestApplication {
	@Autowired
	private UserRepo userRepo;
	private static final Logger LOG = LoggerFactory.getLogger(RestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void testJpaMethods() {
		LOG.info("start testJpaMethods()");
		userRepo.findAll().forEach(System.out::println);
		LOG.info("end testJpaMethods()");
	}

}
