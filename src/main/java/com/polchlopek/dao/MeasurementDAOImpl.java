package com.polchlopek.dao;

import com.polchlopek.data.DataMeasurement;
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

	public List<Measurement> getMeasurements(DataMeasurement dataMeasurement) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

//		from Login log order by log.id desc", Login.class
//		Query theQuery = currentSession.createQuery("delete from Person where id=:personId");
//		theQuery.setParameter("personId", theId);

		// create query
		Query<Measurement> theQuery = currentSession.createQuery("from Measurement meas where " +
				"meas.category.category like :cat_param and " +
				"meas.description like :descr_param ");

		theQuery.setParameter("cat_param", '%'+dataMeasurement.getCategory()+'%');
		theQuery.setParameter("descr_param", '%'+dataMeasurement.getDescription()+'%');

		// execute query and get result list
		List<Measurement> measArray = theQuery.getResultList();

		return measArray;
	}

	public String getDescription(int tmpId) {

		Session currentSession = sessionFactory.getCurrentSession();
		Measurement measurement = currentSession.get(Measurement.class, tmpId);
		String description = measurement.getDescription();

		return description;
	}

	public String getCategory(int tmpId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Measurement measurement = currentSession.get(Measurement.class, tmpId);
		String category = measurement.getCategory().getCategory();

		return category;
	}

	public String getDescriptionAxisX(int tmpId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Measurement measurement = currentSession.get(Measurement.class, tmpId);
		String descriptionAxisX = measurement.getCategory().getDescriptionAxisX();

		return descriptionAxisX;
	}

	public String getDescriptionAxisY(int tmpId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Measurement measurement = currentSession.get(Measurement.class, tmpId);
		String descriptionAxisY = measurement.getCategory().getDescriptionAxisY();

		return descriptionAxisY;
	}

	public void saveMeasurement(Measurement measurement) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(measurement);
	}

	public void deleteMeasurement(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Measurement where id=:measurementId");
		theQuery.setParameter("measurementId", theId);
		theQuery.executeUpdate();
	}


}
