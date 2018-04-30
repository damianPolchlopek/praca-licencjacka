package com.polchlopek.dao;

import com.polchlopek.classToVal.PersonToValUpdate;
import com.polchlopek.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDAO {
	
	public List<Person> getPeople();

	public void savePerson(Person thePerson);

	public Person getPerson(int theId);
	
	public Person getPerson(String nickName);

	public void deletePerson(int theId);

	public PersonToValUpdate getPersonToVal(int theId);
	
}
