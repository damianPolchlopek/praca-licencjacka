package com.polchlopek.controller;

import com.polchlopek.entity.Login;
import com.polchlopek.service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AplicationService applicationService;
	
	@RequestMapping("/showLogin")
	public String listLoginUsers(Model theModel) {

		List<Login> theLogins = applicationService.getLogins();
		theModel.addAttribute("logins", theLogins);

		return "list-login-content";
	}

	
}
