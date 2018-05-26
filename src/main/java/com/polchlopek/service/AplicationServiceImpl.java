package com.polchlopek.service;

import com.polchlopek.validation.PersonToValUpdate;
import com.polchlopek.dao.*;
import com.polchlopek.dto.DataMeasurement;
import com.polchlopek.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AplicationServiceImpl implements AplicationService {
	
	@Autowired
	private PersonDAO personDao;
	
	@Autowired
	private MeasurementDAO measurementDao;
	
	@Autowired
	private MeasurementArrayDAO measurementArrayDao;

	@Autowired
	private MeasurementCategoryDAO measurementCategoryDao;
	
	@Autowired
	private LoginDAO loginDao;



	@Transactional
	public List<Person> getPeople() {
		return personDao.getPeople();
	}

	@Transactional
	public List<Login> getLogins() {
		return loginDao.getLogins();
	}

	@Transactional
	public List<Measurement> getMeasurements() {
		return measurementDao.getMeasurements();
	}

	@Transactional
	public void savePerson(Person thePerson) {
		personDao.savePerson(thePerson);
	}

	@Transactional
	public Person getPerson(int theId) {
		return personDao.getPerson(theId);
	}

	@Transactional
	public Person getPerson(String nickName) {
		return personDao.getPerson(nickName);
	}

	@Transactional
	public void deletePerson(int theId) {
		personDao.deletePerson(theId);
	}

	@Transactional
	public void addPerson(Person tmpPerson) {
		loginDao.addPerson(tmpPerson);
	}

	@Transactional
	public Measurement getMeasurement(int theId) {
		return measurementDao.getMeasurement(theId);
	}

	@Transactional
	public PersonToValUpdate getPersonToVal(int theId) {
		return personDao.getPersonToVal(theId);
	}

	@Transactional
	public List<MeasurementData> getMeasurementData() {
		return measurementDao.getMeasurementData();
	}

	@Transactional
	public List<MeasurementData> getArrayMeassurement(int theId) {
		return measurementArrayDao.getArrayMeassurement(theId);
	}

	@Transactional
	public List<String> getCategories() {
		return measurementCategoryDao.getCategories();
	}

	@Transactional
    public List<Measurement> getMeasurements(DataMeasurement dataMeasurement) {
		return measurementDao.getMeasurements(dataMeasurement);
    }

	@Transactional
	public String getDescription(int tmpId) {
		return measurementDao.getDescription(tmpId);
	}

	@Transactional
	public String getCategory(int tmpId) {
		return measurementDao.getCategory(tmpId);
	}

	@Transactional
	public String getDescriptionAxisX(int tmpId) {
		return measurementDao.getDescriptionAxisX(tmpId);
	}

	@Transactional
	public String getDescriptionAxisY(int tmpId) {
		return measurementDao.getDescriptionAxisY(tmpId);
	}

	@Transactional
	public void saveMeasurement(Measurement measurement) {
		measurementDao.saveMeasurement(measurement);
	}

	@Transactional
	public MeasurementCategory getMeasurementCategory(String category) {
		return measurementCategoryDao.getMeasurementCategory(category);
	}

	@Transactional
	public void deleteMeasurement(int theId) {
		measurementDao.deleteMeasurement(theId);
	}

}
