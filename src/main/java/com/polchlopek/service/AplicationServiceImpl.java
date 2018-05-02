package com.polchlopek.service;

import com.polchlopek.classToVal.PersonToValUpdate;
import com.polchlopek.dao.LoginDAO;
import com.polchlopek.dao.MeasurementArrayDAO;
import com.polchlopek.dao.MeasurementDAO;
import com.polchlopek.dao.PersonDAO;
import com.polchlopek.entity.Login;
import com.polchlopek.entity.Measurement;
import com.polchlopek.entity.MeasurementData;
import com.polchlopek.entity.Person;
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
	
}
