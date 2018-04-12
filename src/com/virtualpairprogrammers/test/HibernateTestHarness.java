package com.virtualpairprogrammers.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.virtualpairprogrammers.domain.Student;
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
		/*
	    // Create a new tutor and student
		Student myStudent = new Student("Alicia Coutts");
		Tutor newTutor = new Tutor("DEF456", "Michael Jung", 35000);
		System.out.println(myStudent);
		
		// Make the student be supervised by that tutor
		myStudent.allocateSupervisor(newTutor);
		System.out.println(myStudent.getSupervisorName());
		
		session.save(myStudent);		
		session.save(newTutor);
		*/
		
		Student foundStudent = (Student) session.get(Student.class, 1);
		System.out.println(foundStudent);
		
		Tutor newSupervisor = (Tutor)session.get(Tutor.class, 2);
		foundStudent.allocateSupervisor(null);
				
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
