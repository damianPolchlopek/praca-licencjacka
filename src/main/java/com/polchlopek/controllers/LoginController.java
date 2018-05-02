package com.polchlopek.controllers;

import com.polchlopek.entity.Login;
import com.polchlopek.service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	// need to inject the login dao
	@Autowired
	private AplicationService applicationService;
	
	@RequestMapping("/showLogin")
	public String listCustomers(Model theModel) {
		
		// get person from the dao
		List<Login> theLogins = applicationService.getLogins();
		
		// add the people to the model
		theModel.addAttribute("logins", theLogins);
		
		return "list-login-content";
	}

	
}
