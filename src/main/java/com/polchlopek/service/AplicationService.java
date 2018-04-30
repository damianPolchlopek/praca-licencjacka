package com.polchlopek.service;

import com.polchlopek.classToVal.PersonToValUpdate;
import com.polchlopek.entity.Login;
import com.polchlopek.entity.Measurement;
import com.polchlopek.entity.MeasurementData;
import com.polchlopek.entity.Person;

import java.util.List;

public interface AplicationService {
	
	public List<Person> getPeople();
	
	public List<Login> getLogins();
	
	public List<Measurement> getMeasurements();

	public void savePerson(Person thePerson);

	public Person getPerson(int theId);
	
	public PersonToValUpdate getPersonToVal(int theId);
	
	public Person getPerson(String nickName);

	public void deletePerson(int theId);

	public void addPerson(Person tmpPerson);

	public Measurement getMeasurement(int theId);

	public List<MeasurementData> getMeasurementData();

	public List<MeasurementData> getArrayMeassurement(int theId);
	
	
}
