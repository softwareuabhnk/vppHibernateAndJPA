package com.virtualpairprogrammers.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a Student enrolled in the college management
 * system (CMS)
 */

@Entity
@Table(name="TBL_STUDENT")
public class Student
{
	// We are using property access, so the annotation are on the get methods
	private int id;	
    private String enrollmentID;
    private String name;
    private String tutorName; // This will become a class soon
    
    private Integer numberOfCourses;
    
    
    /**
     * Required by Hibernate
     */
    
    public Student() {
    
    }
    
    /**
     * Initialises a student with a particular tutor
     */
    public Student(String name, String tutorName)
    {
    	this.name = name;
    	this.tutorName = tutorName;
    }
    
    /**
     * Initialises a student with no pre set tutor
     */
    public Student(String name)
    {
    	this.name = name;
    	this.tutorName = null;
    	this.numberOfCourses = 7;
    }
    
    public double calculateGradePointAverage()
    {
    	// some complex business logic!
    	// we won't need this method on the course, BUT it is import
    	// to remember that classes aren't just get/set pairs - we expect
    	// business logic in here as well.
    	return 0;
    }

	@Override
	public String toString() {
		return "Student [name=" + name + ", tutorName=" + tutorName + "]";
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
    
	public String getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(String enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public String getName() {
		return name.toUpperCase();
	}

	public void setName(String name) {
		this.name = name.toUpperCase();
	}

	public String getTutorName() {
		return tutorName;
	}

	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}

	
	@Column(name="NUM_COURSES")
	public Integer getNumberOfCourses() {
		return numberOfCourses;
	}

	public void setNumberOfCourses(Integer numberOfCourses) {
		this.numberOfCourses = numberOfCourses;
	}
    
}
