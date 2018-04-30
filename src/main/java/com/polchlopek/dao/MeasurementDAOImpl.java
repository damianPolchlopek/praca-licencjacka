package com.polchlopek.dao;

import com.polchlopek.entity.Measurement;
import com.polchlopek.entity.MeasurementData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MeasurementDAOImpl implements MeasurementDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Measurement> getMeasurements() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create query
		Query<Measurement> theQuery = 
				currentSession.createQuery("from Measurement", Measurement.class);
		
		theQuery.setFirstResult(0);
		theQuery.setMaxResults(15);
		
		// execute query and get result list
		List<Measurement> measurements = theQuery.getResultList();
		
		return measurements;
	}

	public Measurement getMeasurement(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// execute query and get result list
		Measurement measurement = currentSession.get(Measurement.class, theId);
		
		return measurement;
	}

	public List<MeasurementData> getMeasurementData() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create query
		Query<MeasurementData> theQuery = currentSession.createQuery("from MeasurementData");
		
		// execute query and get result list
		List<MeasurementData> measArray = theQuery.getResultList();
		
		return measArray;
	}

}
