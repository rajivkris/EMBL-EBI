package com.emblebi.person.management.personmanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "find_all_person", query = "select p from Person p")
public class Person {
	
	@Id
	@GeneratedValue
	private int id;
	private String first_name;
	private String last_name;
	private int age;
	private String favourite_colour;
	
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Hobby> hobby;
	
	public Person() {
		
	}
	
	public Person(String first_name, String last_name, int age, String favourite_colour, List<Hobby> hobby) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.favourite_colour = favourite_colour;
		this.hobby = hobby;
	}

	public Person(int id, String first_name, String last_name, int age, String favourite_colour, List<Hobby> hobby) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.favourite_colour = favourite_colour;
		this.hobby = hobby;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFavourite_colour() {
		return favourite_colour;
	}

	public void setFavourite_colour(String favourite_colour) {
		this.favourite_colour = favourite_colour;
	}

	public List<Hobby> getHobby() {
		return hobby;
	}

	public void setHobby(List<Hobby> hobby) {
		this.hobby = hobby;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
