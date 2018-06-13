package com.polchlopek.controller;

import com.polchlopek.entity.Person;
import com.polchlopek.validation.PersonToUpdatePassword;
import com.polchlopek.validation.PersonToValAdd;
import com.polchlopek.validation.PersonToValUpdate;
import com.polchlopek.service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/person")
@SessionAttributes({"personToUpdatePassword"})
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
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword =  "{bcrypt}" + passwordEncoder.encode(personToSave.getPassword());
			personToSave.setPassword(encodedPassword);
			applicationService.savePerson(personToSave);
			return "redirect:/person/showPerson";
		}
	}

	@RequestMapping("/showFormUpdatePassword")
	public String showFormUpdatePassword(@RequestParam("personId") int theId,
										 Model theModel) {

		PersonToUpdatePassword personToUpdatePassword = new PersonToUpdatePassword();
		personToUpdatePassword.setIdPerson(theId);
		theModel.addAttribute("personToUpdatePassword", personToUpdatePassword);

		return "update-user-password";
	}

	@RequestMapping("/updatePassword")
	public String updatePassword(@Valid @ModelAttribute("personToUpdatePassword") PersonToUpdatePassword personToUpdatePassword,
							 BindingResult theBindingResult) {

		if(theBindingResult.hasErrors()) {
			return "update-user-password";
		}
		else {
			Person thePerson = applicationService.getPerson(personToUpdatePassword.getIdPerson());

			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword =  "{bcrypt}" + passwordEncoder.encode(personToUpdatePassword.getNewPassword1());
			thePerson.setPassword(encodedPassword);

			String passwordFromDb = thePerson.getPassword().substring(8, thePerson.getPassword().length());
			String enteredPassword = personToUpdatePassword.getNewPassword1().trim();

			if(passwordEncoder.matches(enteredPassword, passwordFromDb )){

				if (personToUpdatePassword.getNewPassword1().equals(
						personToUpdatePassword.getNewPassword2())){
					applicationService.savePerson(thePerson);
				}
				else{
					return "update-password-incorrect-new-pass";
				}
			}
			else{
				return "update-password-incorrect-new-pass";
			}

			return "redirect:/person/showPerson";
		}
	}

	@RequestMapping("/savePersonUpdate")
	public String savePersonUpdate(@Valid @ModelAttribute("personToValUpdate") PersonToValUpdate thePerson,
			BindingResult theBindingResult) {

		System.out.println("Person: " + thePerson);

		if(theBindingResult.hasErrors()) {
			return "update-person-information";
		}
		else {
			Person personToSave = new Person(thePerson);
			String oldPassword = applicationService.getPassword(thePerson.getId());
			personToSave.setPassword(oldPassword);
			applicationService.savePerson(personToSave);

			return "redirect:/person/showPerson";
		}

	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("personId") int theId, 
				Model theModel) {

		PersonToValUpdate thePerson = applicationService.getPersonToVal(theId);
		theModel.addAttribute("personToValUpdate", thePerson);

		return "update-person-information";
	}
	
	@RequestMapping("/delete")
	public String deletePerson(@RequestParam("personId") int theId) {
		
		applicationService.deletePerson(theId);
		
		return "redirect:/person/showPerson";
	}

}
