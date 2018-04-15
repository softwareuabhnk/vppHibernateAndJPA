package com.virtualpairprogrammers.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.virtualpairprogrammers.domain.Student;
import com.virtualpairprogrammers.domain.Subject;
import com.virtualpairprogrammers.domain.Tutor;

public class HibernateTestHarness 
{
	private static SessionFactory sessionFactory = null;

	public static void main(String[] args)
	{	
		SessionFactory sf = getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		// save the student to the database
	
	    // Create a new tutor and student
		Student myStudent = new Student("Alicia Coutts", "5-COU-2009");
		Tutor newTutor = new Tutor("DEF456", "Michael Jung", 35000);
		System.out.println(myStudent);
		
		// Make the student be supervised by that tutor
		myStudent.allocateSupervisor(newTutor);
		System.out.println(myStudent.getSupervisorName());
		
		session.save(myStudent);		
		session.save(newTutor);
		
		//test out creating a couple fo subjects
		
		Subject subject1 = new Subject("Math", 3);
		Subject subject2 = new Subject("Science", 6);
		
	    session.save(subject1);
	    session.save(subject2);
		
	    //newTutor.addSubjectToQualifications(subject1);
	    //newTutor.addSubjectToQualifications(subject2);
	    subject1.addTutorToSubject(newTutor);
	    subject2.addTutorToSubject(newTutor);
	    
	    Tutor secondTutor = new Tutor("GHJ3838", "Ben Ainslie", 3883833);
	    secondTutor.addSubjectToQualifications(subject2);
	    subject2.addTutorToSubject(secondTutor);
	    
	    session.save(secondTutor);
	    
	
//		Tutor newTutor = new Tutor("ABC844", "Adrian Nathan", 38000);
//		
//		Student student1 = new Student("Rebecca Soni", "1-SON-2012");
//		Student student2 = new Student("Zou Kai", "2-KAI-2009");
//		Student student3 = new Student("Chris Hoy", "3-HOY-1997");
//		
//		session.save(student1);
//		session.save(student2);
//		session.save(student3);
//		
//		session.save(newTutor);
//		
//		newTutor.addStudentToSupervisionGroup(student1);
//		newTutor.addStudentToSupervisionGroup(student2);
//		newTutor.addStudentToSupervisionGroup(student3);
//				
//		System.out.println(student1.toString());
//		System.out.println(newTutor);
		
		
//		Tutor myTutor = (Tutor)session.get(Tutor.class, 1);
//		Set<Student> students = myTutor.getSuperVisionGroup();
//		for(Student next : students) {
//			System.out.println(next);
//    	}
		
//		Student myStudent = (Student)session.get(Student.class, 2);
//		Tutor myStudentTutor = myStudent.getSupervisor();
//		System.out.println(myStudentTutor);
//		
		tx.commit();
		session.close();
	
	}

	public static SessionFactory getSessionFactory()
	{
		if (sessionFactory == null)
		{
			Configuration configuration = new Configuration();
			configuration.configure();
			
			ServiceRegistry serviceRegistry = new 
					ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();        

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}			
}
