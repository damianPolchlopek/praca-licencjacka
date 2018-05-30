package com.polchlopek.dao;

import com.polchlopek.entity.MeasurementData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MeasurementArrayDAOImpl implements MeasurementArrayDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<MeasurementData> getArrayMeassurement(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<MeasurementData> theQuery = 
				currentSession.createQuery("from MeasurementData where id_meas=:theId");
		
		theQuery.setParameter("theId", theId);

		return theQuery.getResultList();
	}

}
