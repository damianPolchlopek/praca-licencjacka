package com.polchlopek.controller;

import com.polchlopek.entity.Person;
import com.polchlopek.service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController {

    @Autowired
    private AplicationService applicationService;

    @RequestMapping("/")
    public String showHome() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Person tmpPerson = applicationService.getPerson(currentPrincipalName);
        applicationService.addPerson(tmpPerson);

        return "main";
    }

    @GetMapping("/admin")
    public String showAdmin() {
        return "admin";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @GetMapping("/help")
    public String showHelp() {
        return "help-panel";
    }

}
