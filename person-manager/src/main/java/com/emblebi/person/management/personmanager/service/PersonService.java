package com.emblebi.person.management.personmanager.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emblebi.person.management.personmanager.helper.PersonHelper;
import com.emblebi.person.management.personmanager.model.PersonRoot;
import com.emblebi.person.management.personmanager.repository.PersonRepository;

@Service
@Path("/persons")
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersons() {
		if (repository.findAllPersons().size() == 0) {
			return Response.ok().entity(new PersonRoot()).build();
		}
		PersonRoot savedPersons = PersonHelper.mapPersonEntityToPerson(repository.findAllPersons());
        return Response.ok().entity(savedPersons).build();
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/person")
	public Response createPerson(PersonRoot person) {
		repository.insertPerson(PersonHelper.mapPersonToPersonEntity(person, -1));
		PersonRoot savedPersons = PersonHelper.mapPersonEntityToPerson(repository.findAllPersons());
		return Response.ok().entity(savedPersons).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/person/{id}")
	public Response updatePerson(@PathParam(value = "id") int id, PersonRoot person) {
		repository.updatePerson(PersonHelper.mapPersonToPersonEntity(person, id));
		PersonRoot savedPersons = PersonHelper.mapPersonEntityToPerson(repository.findAllPersons());
		return Response.ok().entity(savedPersons).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/person/{id}")
	public Response deletePerson(@PathParam(value = "id") int id) {
		repository.deletePerson(id);
		PersonRoot savedPersons = PersonHelper.mapPersonEntityToPerson(repository.findAllPersons());
		return Response.ok().entity(savedPersons).build();
	}
	
}
