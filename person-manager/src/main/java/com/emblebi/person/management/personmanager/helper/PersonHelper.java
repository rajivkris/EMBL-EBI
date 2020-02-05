package com.emblebi.person.management.personmanager.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.emblebi.person.management.personmanager.entity.Hobby;
import com.emblebi.person.management.personmanager.entity.Person;
import com.emblebi.person.management.personmanager.model.PersonResource;
import com.emblebi.person.management.personmanager.model.PersonRoot;

public class PersonHelper {

	public static PersonRoot mapPersonEntityToPerson(List<Person> persons) {
		final List<PersonResource> perResources = new ArrayList<PersonResource>();
		for (Person person : persons) {
			final List<String> hobbies = person.getHobby().stream().map(hobby -> hobby.getHobby()).collect(Collectors.toList());
			final PersonResource ps = new PersonResource();
			
			ps.setFirst_name(person.getFirst_name());
			ps.setLast_name(person.getLast_name());
			ps.setAge(person.getAge());
			ps.setFavourite_colour(person.getFavourite_colour());
			ps.setId(person.getId());
			ps.setHobby(hobbies);
			ps.setSelfRef("/api/persons/person/" + person.getId());
			perResources.add(ps);
		}
		
		final PersonRoot personRoot = new PersonRoot();
		personRoot.setPersons(perResources);
		return personRoot;
	}
	
	public static Person mapPersonToPersonEntity(PersonRoot root, int id) {
		if (root.getPersons().size() == 1) {
			final PersonResource ps = root.getPersons().get(0);
			final Person person = new Person();
			if (id != -1) {
				person.setId(id);
			}
			person.setFirst_name(ps.getFirst_name());
			person.setLast_name(ps.getLast_name());
			person.setFavourite_colour(ps.getFavourite_colour());
			person.setAge(ps.getAge());
			
			final List<Hobby> hobbies = ps.getHobby().stream().map(hobby -> {
									Hobby hob = new Hobby();
									hob.setPerson(person);
									hob.setHobby(hobby);
									return hob;
								}).collect(Collectors.toList());
			person.setHobby(hobbies);
			return person;
		}
		return null;
	}
}
