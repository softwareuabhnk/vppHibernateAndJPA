package com.virtualpairprogrammers.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Generated;

@Entity
public class Tutor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String staffId;
	private String name;
	private int salary;
	
	//Required by Hibernate	
	public Tutor() {

	}

	//	Business constructor
	public Tutor(String staffId, String name, int salary) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
	}
	
	public String getName() {
		return this.name;
	}
	
}
