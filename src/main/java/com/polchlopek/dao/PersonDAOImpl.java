package com.polchlopek.dao;

import com.polchlopek.classToVal.PersonToValUpdate;
import com.polchlopek.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PersonDAOImpl implements PersonDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Person> getPeople() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create query
		Query<Person> theQuery = 
				currentSession.createQuery("from Person order by lastName", Person.class);
		
		theQuery.setFirstResult(0);
		theQuery.setMaxResults(15);
		
		// execute query and get result list
		List<Person> people = theQuery.getResultList();
		
		return people;
	}

	public void savePerson(Person thePerson) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		System.out.println("DAO person: przed zapisem");
		// save/update the person
		currentSession.saveOrUpdate(thePerson);
		//currentSession.save(thePerson);


	}

	public Person getPerson(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Person thePerson = currentSession.get(Person.class, theId);
		
		return thePerson;
	}

	public Person getPerson(String nickName) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// get person form database need to login to application
		Query<Person> theQuery = currentSession.createQuery("from Person where username=:nickName");
		
		theQuery.setParameter("nickName", nickName);
		
		List<Person> thePerson = theQuery.getResultList();
		
		System.out.println("Login: " + thePerson);
		
		if(thePerson.isEmpty()) {
			return null;
		}
		else {
			return thePerson.get(0);
		}

	}

	public void deletePerson(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Person where id=:personId");
		
		theQuery.setParameter("personId", theId);
		
		theQuery.executeUpdate();
	}

	public PersonToValUpdate getPersonToVal(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Person thePerson = currentSession.get(Person.class, theId);
		
		PersonToValUpdate personToVal = new PersonToValUpdate(thePerson);
		
		return personToVal;
	}

}
