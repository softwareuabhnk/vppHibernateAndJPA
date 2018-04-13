package com.virtualpairprogrammers.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents a Student enrolled in the college management
 * system (CMS)
 */
@Entity
//@Table(name="TBL_STUDENT")
public class Student
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
    private String enrollmentID;
    private String name;
    
    // Relationship I used class tutor as class name
    @ManyToOne
    @JoinColumn(name="TUTOR_FK")
    private Tutor supervisor;
    
    /*
     * Required by Hibernate
     */
    public Student()
    {
    	
    }
    
    /**
     * Initialises a student with a particular tutor
     */
    public Student(String name, Tutor supervisor)
    {
    	this.name = name;
    	this.supervisor = supervisor;
    }
    
    /**
     * Initialises a student with no pre set tutor
     */
    public Student(String name, String enrollmentID)
    {
    	this.name = name;
    	this.enrollmentID = enrollmentID;
   // 	this.supervisor = null;
    }
    
    public double calculateGradePointAverage()
    {
    	// some complex business logic!
    	// we won't need this method on the course, BUT it is import
    	// to remember that classes aren't just get/set pairs - we expect
    	// business logic in here as well.
    	return 0;
    }
    
    public void allocateSupervisor(Tutor newSupervisor) {
    	this.supervisor = newSupervisor;
    	newSupervisor.getModifiableSupervisionGroup().add(this);
    }
    
    public String getSupervisorName() {
    	return this.supervisor.getName();
    }
    
    
    
    @Override
	public String toString() {
		return "Student [id=" + id + ", enrollmentID=" + enrollmentID + ", name=" + name + ", supervisor=" + supervisor
				+ "]";
	}

	public int getId()
    {
    	return this.id;
    }

	public String geEnrollmentId() {
		return this.enrollmentID;
	}

	public Tutor getSupervisor() {
		
		return this.supervisor;
	}
}
