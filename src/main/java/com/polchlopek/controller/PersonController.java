package com.polchlopek.controller;

import com.polchlopek.entity.Person;
import com.polchlopek.validation.PersonToValAdd;
import com.polchlopek.validation.PersonToValUpdate;
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

	@Autowired
	private AplicationService applicationService;

	@GetMapping("/showPerson")
	public String listPeople(Model theModel) {

		List<Person> thePeople = applicationService.getPeople();
		theModel.addAttribute("people", thePeople);
		
		return "list-person-content";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		PersonToValAdd thePersonToVal = new PersonToValAdd();
		theModel.addAttribute("personToValAdd", thePersonToVal);
		
		return "add-person";
	}

	@RequestMapping("/savePerson")
	public String savePerson(@Valid @ModelAttribute("personToValAdd") PersonToValAdd thePerson,
			BindingResult theBindingResult) {

		if(theBindingResult.hasErrors()) {
			return "add-person";
		}
		else {
			Person personToSave = new Person(thePerson);
			applicationService.savePerson(personToSave);
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
			applicationService.savePerson(personToSave);
			return "redirect:/person/showPerson";
		}

	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("personId") int theId, 
				Model theModel) {

		PersonToValUpdate thePerson = applicationService.getPersonToVal(theId);
		theModel.addAttribute("personToValUpdate", thePerson);

		return "update-person";
	}
	
	@RequestMapping("/delete")
	public String deletePerson(@RequestParam("personId") int theId) {
		
		applicationService.deletePerson(theId);
		
		return "redirect:/person/showPerson";
	}

}
