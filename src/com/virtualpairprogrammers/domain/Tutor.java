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
	
	// Notation mappedBy means = Already mapped by property supervisor (in class Student) 
	@OneToMany(mappedBy="supervisor")
	//@MapKey(name="enrollmentID")
	//@OrderBy("name")
	//@OrderColumn(name = "student_order")
	//@JoinColumn(name="TUTOR_FK")
	private Set<Student> supervisionGroup;
	
	//Required by Hibernate	
	public Tutor() {

	}

	//	Business constructor
	public Tutor(String staffId, String name, int salary) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
		this.supervisionGroup = new HashSet<Student>();
	}
	
	public void addStudentToSupervisionGroup(Student studentToAdd) {
	this.supervisionGroup.add(studentToAdd);
	studentToAdd.allocateSupervisor(this);
	}
	
	public Set<Student> getSuperVisionGroup(){
		Set<Student> unmodifiable = Collections.unmodifiableSet(this.supervisionGroup);
		return unmodifiable;
	}
	
	public Set<Student> getModifiableSupervisionGroup(){
		return this.supervisionGroup;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "Tutor [id=" + id + ", staffId=" + staffId + ", name=" + name + "]";
	}
	
	
	
}
