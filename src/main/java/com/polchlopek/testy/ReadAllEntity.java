package com.polchlopek.testy;

import com.polchlopek.entity.Login;
import com.polchlopek.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ReadAllEntity {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Login.class)
								.buildSessionFactory();
		
		// create session 
		Session session = factory.getCurrentSession();
			
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Person> thePeople = session.createQuery("from Person").getResultList();
			
			//display the students
			displayStudents(thePeople);
			
			session.getTransaction().commit();	
			
		}
		catch (Exception e) {
			factory.close();
		}
	
	}
	
	private static void displayStudents(List<Person> theStudents) {
		for(Person tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
