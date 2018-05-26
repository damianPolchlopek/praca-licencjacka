package com.polchlopek.controller;

import com.polchlopek.service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginPanelController {
	
	@Autowired
	private AplicationService applicationService;

    @GetMapping("/showPlainForm")
    public String showPlainForm() {
        return "fancy-login";
    }

	@GetMapping("/loginOk")
	public String showMenu() {		
		return "main";
	}

}
