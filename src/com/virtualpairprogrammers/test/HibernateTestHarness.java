package com.virtualpairprogrammers.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.virtualpairprogrammers.domain.Student;

public class HibernateTestHarness {
	
	private static SessionFactory sessionFactory = null;
	
	
	public static void main(String[] args) {		
		
	
		
		//System.out.println(testStudent + " Has a grade point average of " + testStudent.calculateGradePointAverage());
		//System.out.println("This student has an id of : " + testStudent.getId());
		// Save the student to the database	
		SessionFactory sf = getSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		// Search for Student
		//Student myStudent = (Student)session.get(Student.class, 3);
		
		// Create Student
		//Student myStudent = new Student("Jessica Ennis", "Toni Minichiello");
		Student myStudent = new Student("Debera Fish");
		
		//System.out.println("This student " + myStudent + " has id of : " + myStudent.getId());
		//myStudent.setTutor("Dave Fish");
		
		session.save(myStudent);	
		//session.delete(myStudent);
		
		Student myStudent2 = (Student)session.get(Student.class, 3);
		
		tx.commit();
		session.close();
		
		System.out.println("This student " + myStudent + " has a name : " + myStudent.getName());
		System.out.println("This student " + myStudent2 + " has a name : " + myStudent.getName());
		
	}
	
public static SessionFactory getSessionFactory() {
		
		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
			configuration.configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.buildServiceRegistry();
			
			sessionFactory = 
					configuration.buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}
}
