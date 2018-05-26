package com.polchlopek.service;

import com.polchlopek.validation.PersonToValUpdate;
import com.polchlopek.dto.DataMeasurement;
import com.polchlopek.entity.*;

import java.util.List;


public interface AplicationService {
	
	List<Person> getPeople();

	List<Login> getLogins();

	List<Measurement> getMeasurements();

	void savePerson(Person thePerson);

	Person getPerson(int theId);

	PersonToValUpdate getPersonToVal(int theId);

	Person getPerson(String nickName);

	void deletePerson(int theId);

	void addPerson(Person tmpPerson);

	Measurement getMeasurement(int theId);

	List<MeasurementData> getMeasurementData();

	List<MeasurementData> getArrayMeassurement(int theId);

	List<String> getCategories();

	List<Measurement> getMeasurements(DataMeasurement dataMeasurement);

	String getDescription(int tmpId);

	String getCategory(int tmpId);

	String getDescriptionAxisX(int tmpId);

	String getDescriptionAxisY(int tmpId);

	void saveMeasurement(Measurement measurement);

	MeasurementCategory getMeasurementCategory(String category);

    void deleteMeasurement(int theId);
}
