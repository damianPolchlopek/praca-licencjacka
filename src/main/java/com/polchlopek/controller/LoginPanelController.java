package com.polchlopek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginPanelController {

    @GetMapping("/showPlainForm")
    public String showPlainForm() {
        return "fancy-login";
    }

	@GetMapping("/loginOk")
	public String showMenu() {		
		return "main";
	}

}
