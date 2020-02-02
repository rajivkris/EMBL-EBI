package com.emblebi.person.management.personmanager.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

@Service
@Path("/person")
public class PersonService {
	@GET
    @Produces("text/plain")
    public String hello() {
        return "Hello from Spring";
    }
}
