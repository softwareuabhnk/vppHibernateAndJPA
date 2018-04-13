package com.virtualpairprogrammers.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;

import org.hibernate.annotations.Generated;

@Entity
public class Tutor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String staffId;
	private String name;
	private int salary;
	
	
	@OneToMany
	//@MapKey(name="enrollmentID")
	//@OrderBy("name")
	@OrderColumn(name = "student_order")
	@JoinColumn(name="TUTOR_FK")
	private List<Student> supervisionGroup;
	
	//Required by Hibernate	
	public Tutor() {

	}

	//	Business constructor
	public Tutor(String staffId, String name, int salary) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
		this.supervisionGroup = new ArrayList<Student>();
	}
	
	public void addStudentToSupervisionGroup(Student studentToAdd) {
	this.supervisionGroup.add(studentToAdd);
	}
	
	public List<Student> getSuperVisionGroup(){
		List<Student> unmodifiable = Collections.unmodifiableList(this.supervisionGroup);
		return unmodifiable;
	}
	
	public String getName() {
		return this.name;
	}
	
}
