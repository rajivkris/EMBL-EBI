package com.emblebi.person.management.personmanager.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties({"id"})
public class PersonResource {

	private int id;
	private String first_name;
	private String last_name;
	private int age;
	private String favourite_colour;
	private List<String> hobby;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonGetter
	public String getFirst_name() {
		return first_name;
	}
	
	@JsonSetter
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	@JsonGetter
	public String getLast_name() {
		return last_name;
	}
	
	@JsonSetter
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	@JsonGetter
	public int getAge() {
		return age;
	}
	
	@JsonSetter
	public void setAge(int age) {
		this.age = age;
	}
	
	@JsonGetter
	public String getFavourite_colour() {
		return favourite_colour;
	}
	
	@JsonSetter
	public void setFavourite_colour(String favourite_colour) {
		this.favourite_colour = favourite_colour;
	}
	
	@JsonGetter
	public List<String> getHobby() {
		return hobby;
	}
	
	@JsonSetter
	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
	
	
}
