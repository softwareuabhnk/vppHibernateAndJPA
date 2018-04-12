package com.virtualpairprogrammers.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.virtualpairprogrammers.domain.Student;

public class HibernateTestHarness 
{
	private static SessionFactory sessionFactory = null;

	public static void main(String[] args)
	{	
		SessionFactory sf = getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		// save the student to the database
		Student testStudent = new Student("Jessica Ennis", "Toni Minichiello");
		//Student testStudent = new Student("Jess Child", "Toni Minichiello");
		System.out.println(testStudent);
		session.save(testStudent);	
		tx.commit();
		System.out.println("This student has an id of : " + testStudent.getId());
			
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
