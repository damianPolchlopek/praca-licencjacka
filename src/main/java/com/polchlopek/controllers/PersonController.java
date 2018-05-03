package com.polchlopek.controllers;

import com.polchlopek.entity.Person;
import com.polchlopek.classToVal.PersonToValAdd;
import com.polchlopek.classToVal.PersonToValUpdate;
import com.polchlopek.service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {

	// need to inject the person dao
	@Autowired
	private AplicationService applicationService;
	
	@GetMapping("/showPerson")
	public String listPeople(Model theModel) {
		
		// get person from the dao
		List<Person> thePeople = applicationService.getPeople();
		
		// add the people to the model
		theModel.addAttribute("people", thePeople);
		
		return "list-person-content";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// zostalo zmienione z update na add UWAGA !!!!!!!!
		// create model attribute to bind form data
		PersonToValUpdate thePersonToVal = new PersonToValUpdate();
		
		theModel.addAttribute("personToVal", thePersonToVal);
		
		return "add-person";
	}

	@RequestMapping("/savePerson")
	public String savePerson(@Valid @ModelAttribute("personToVal") PersonToValAdd thePerson,
			BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "add-person";
		}
		else {
			Person personToSave = new Person(thePerson);
			// save the person using our service
			System.out.println("Dodanie");
			applicationService.savePerson(personToSave);
			System.out.println("Przekierowuje do listy !!!");
			return "redirect:/person/showPerson";
		}
	}
	
	@RequestMapping("/savePersonUpdate")
	public String savePersonUpdate(@Valid @ModelAttribute("personToValUpdate") PersonToValUpdate thePerson,
			BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "update-person";
		}
		else {
			Person personToSave = new Person(thePerson);
			System.out.println("UpdateersonToVal: " + thePerson);
			System.out.println("Update person: " + personToSave);
			// save the person using our service
			applicationService.savePerson(personToSave);
			return "redirect:/person/showPerson";
		}

	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("personId") int theId, 
				Model theModel) {

		// get the person from our service
		PersonToValUpdate thePerson = applicationService.getPersonToVal(theId);
		
		// set person as a model attribute to pre-populate the form
		theModel.addAttribute("personToValUpdate", thePerson);
		
		// send over to our form
		return "update-person";
	}
	
	@GetMapping("/delete")
	public String deletePerson(@RequestParam("personId") int theId) {
		
		applicationService.deletePerson(theId);
		
		return "redirect:/person/showPerson";
	}

	
}
