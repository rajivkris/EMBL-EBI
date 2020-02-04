package com.emblebi.person.management.personmanager.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.emblebi.person.management.personmanager.entity.Person;

@Repository
@Transactional
public class PersonRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<Person> findAllPersons() {
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_person", Person.class);
		return namedQuery.getResultList();
	}
	
	public Person findPersonById(int id) {
		return entityManager.find(Person.class, id);
	}
	
	public Person updatePerson(Person person) {
		return entityManager.merge(person);
	}
	
	public Person insertPerson(Person person) {
		return entityManager.merge(person);
	}
	
	public void deletePerson(int id) {
		Person person = findPersonById(id);
		entityManager.remove(person);
	}
	
}
