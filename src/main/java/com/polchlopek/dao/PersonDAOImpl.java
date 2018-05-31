package com.polchlopek.dao;

import com.polchlopek.validation.PersonToValUpdate;
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

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Person> theQuery = 
				currentSession.createQuery("from Person order by lastName", Person.class);
		
		theQuery.setFirstResult(0);
		theQuery.setMaxResults(15);

		return theQuery.getResultList();
	}

	public void savePerson(Person thePerson) {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(thePerson);
	}

	public Person getPerson(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.get(Person.class, theId);
	}

	public Person getPerson(String nickName) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Person> theQuery = currentSession.createQuery("from Person where username=:nickName");
		theQuery.setParameter("nickName", nickName);
		List<Person> thePerson = theQuery.getResultList();

		if(thePerson.isEmpty()) {
			return null;
		}
		else {
			return thePerson.get(0);
		}

	}

	public void deletePerson(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Person where id=:personId");
		theQuery.setParameter("personId", theId);
		theQuery.executeUpdate();
	}

	public PersonToValUpdate getPersonToVal(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		Person thePerson = currentSession.get(Person.class, theId);
		return new PersonToValUpdate(thePerson);
	}

}