package com.polchlopek.dao;

import com.polchlopek.entity.Login;
import com.polchlopek.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<Login> getLogins() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Login> theQuery =
				currentSession.createQuery("from Login log order by log.id desc", Login.class);
		
		theQuery.setFirstResult(0);
		theQuery.setMaxResults(10);

		List<Login> logins = theQuery.getResultList();
		
		return logins;
	}

	public void addPerson(Person tmpPerson) {

		Session currentSession = sessionFactory.getCurrentSession(); 

		java.util.Date utilDate = new java.util.Date();
		Date sqlDate = new Date(utilDate.getTime());
		java.sql.Time sqlTime = new java.sql.Time(System.currentTimeMillis());
		
		Login tmpLogin = new Login(sqlDate, sqlTime, "Krakow", tmpPerson);

		currentSession.save(tmpLogin);
	}
}
