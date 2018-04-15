package com.virtualpairprogrammers.test;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.virtualpairprogrammers.domain.Student;
import com.virtualpairprogrammers.domain.Tutor;

public class HibernateTestHarness 
{

	public static void main(String[] args)
	{	
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDatabaseConfig");
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		
		//Cascading enabled in Tutor
		Tutor t1 = new Tutor("ABC123", "Laura Bennett", 3247474);
		em.persist(t1);
		
		
		// This only works because  we are cascading persists from tutor to student
		t1.createAndAddToSupervsionGroup("Ryan Bailey", "1-BAT-2011");
		t1.createAndAddToSupervsionGroup("Peg Dow", "1-DOW-2011");
		t1.createAndAddToSupervsionGroup("Lisa Andersson", "1-AND-2011");
		
		Set<Student> allStudents  = t1.getSuperVisionGroup();
		
		System.out.println(allStudents);
		
		tx.commit();
		em.close();
		
	}
}
