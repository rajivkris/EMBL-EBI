package com.emblebi.person.management.personmanager.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class PersonRoot {

	private List<PersonResource> persons;

	@JsonGetter("person")
	public List<PersonResource> getPersons() {
		return persons;
	}

	@JsonSetter("person")
	public void setPersons(List<PersonResource> persons) {
		this.persons = persons;
	}
	
}
