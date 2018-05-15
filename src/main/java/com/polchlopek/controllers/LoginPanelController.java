package com.polchlopek.controllers;

import com.polchlopek.classToVal.PersonToValLogin;
import com.polchlopek.entity.Person;
import com.polchlopek.service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginPanelController {
	
	@Autowired
	private AplicationService applicationService;

    @GetMapping("/showPlainForm")
    public String showPlainForm() {
		System.out.println("/login/showPlainForm");
        return "fancy-login";
    }

    // do wyrzucenia prawdopodobnie bedzie
	@GetMapping("/showForm")
	public String showForm(Model theModel) {
		
		// create a student object		
		PersonToValLogin ptvl = new PersonToValLogin();
		
		// add student object to the model
		theModel.addAttribute("userToValLogin", ptvl);
		
		return "stare/login-panel";
	}
	
	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("userToValLogin") PersonToValLogin user,
								BindingResult theBindingResult) {	
		
		Person tmpPerson = applicationService.getPerson(user.getNickName());
		
		boolean correctLogin = false;
		
		if(tmpPerson != null && tmpPerson.getPassword().equals(user.getPassword()) ) {
			correctLogin = true;
		}
		else if (tmpPerson == null) {
			System.out.println("Nie ma takiego uzytkownika w bazie");
		}
		else {
			System.out.println("Niepoprawne has�o");
		}
		
		if(correctLogin) {
			applicationService.addPerson(tmpPerson);
			System.out.println("User: " + user.getNickName() + ", pass: " + user.getPassword());
			System.out.println("Logowanie poprawne");
			return "stare/main";
		}
		else {
			System.out.println("Logowanie bad !!!!!!!!");
			return "stare/login-panel";
		}
		
	}





	@RequestMapping("/loginOk")
	public String showMenu() {		
		return "main";
	}
	
}
