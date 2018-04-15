package com.virtualpairprogrammers.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	@OneToMany(mappedBy="supervisor", cascade=CascadeType.PERSIST)
	//@MapKey(name="enrollmentID")
	//@OrderBy("name")
	//@OrderColumn(name = "student_order")
	//@JoinColumn(name="TUTOR_FK")
	private Set<Student> supervisionGroup;
	
	@ManyToMany(mappedBy="qualifiedTutors")
	public Set<Subject> subjectsQualifiedToTeach;
	
	
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
		this.subjectsQualifiedToTeach = new HashSet<Subject>();
	}
	
	
	public void addSubjectToQualifications(Subject subject) {
		this.subjectsQualifiedToTeach.add(subject);
		subject.getQualifiedTutors().add(this);
	}
	
	public void addStudentToSupervisionGroup(Student studentToAdd) {
	this.supervisionGroup.add(studentToAdd);
	studentToAdd.allocateSupervisor(this);
	}
	
	public Set<Subject> getSubjects(){
		return this.subjectsQualifiedToTeach;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((staffId == null) ? 0 : staffId.hashCode());
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
		Tutor other = (Tutor) obj;
		if (staffId == null) {
			if (other.staffId != null)
				return false;
		} else if (!staffId.equals(other.staffId))
			return false;
		return true;
	}

	public void createAndAddToSupervsionGroup(String studentName, String enrollmentId) {
		Student student = new Student(studentName, enrollmentId);
		this.addStudentToSupervisionGroup(student);
	}
	
	
	
}
