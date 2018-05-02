package com.polchlopek.controllers;

import com.polchlopek.entity.MeasurementData;
import com.polchlopek.service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private AplicationService applicationService;

    @RequestMapping("/")
    public String showHome() {
        return "temp";
    }

    @RequestMapping("/admin")
    public String showAdmin() {
        return "admin";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @RequestMapping("/help")
    public String showHelp(Model theModel) {

        // to tutaj jest raczej niepotrzebne - trzeba wyrzucic
        System.out.println("przed");
        List<MeasurementData> vectorYValue = applicationService.getMeasurementData();
        System.out.println("List: " + vectorYValue);
        theModel.addAttribute("vectorY", vectorYValue);

        return "help";
    }

}
