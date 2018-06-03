package com.polchlopek.dao;

import com.polchlopek.validation.PersonToValUpdate;
import com.polchlopek.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDAO {
	
	List<Person> getPeople();

	void savePerson(Person thePerson);

	Person getPerson(int theId);
	
	Person getPerson(String nickName);

	void deletePerson(int theId);

	PersonToValUpdate getPersonToVal(int theId);

    boolean isUsername(String nickName);


}
