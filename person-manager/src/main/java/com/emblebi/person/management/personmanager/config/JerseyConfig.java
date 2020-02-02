package com.emblebi.person.management.personmanager.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.emblebi.person.management.personmanager.service.PersonService;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(PersonService.class);
	}
}
