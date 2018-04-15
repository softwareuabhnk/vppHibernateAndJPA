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
		
//		Tutor t1 = new Tutor("ABC123", "Laura Bennett", 3247474);
//		em.persist(t1);
//		
//		Student s1 = new Student("Ryan Bailey", "1-BAI-2011");
//	    t1.addStudentToSupervisionGroup(s1);
//		
//		Student s2 = new Student("Luke Adams", "2-ADA-2009");
//	    t1.addStudentToSupervisionGroup(s2);
//		
//		Student s3 = new Student("Angie Bainbridge", "3-BAI-2008");
//		t1.addStudentToSupervisionGroup(s3);
//			
//		em.persist(s1);
//		em.persist(s2);
//		em.persist(s3);
//		
//		Set<Student> allStudents  = t1.getSuperVisionGroup();
//		
//		System.out.println(allStudents);
		
		Tutor myTutor = em.find(Tutor.class, 1);
		System.out.println(myTutor);
		
		Set<Student> students = myTutor.getSuperVisionGroup();
		for (Student next: students) {
			System.out.println(next);
		}
		
		Student student = em.find(Student.class, 2);
		System.out.println(student);
		
		em.remove(student);
		
		tx.commit();
		em.close();
		
	}
}
