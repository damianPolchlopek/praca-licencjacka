package com.polchlopek.controllers;

import com.polchlopek.classToVal.PersonToValAdd;
import com.polchlopek.entity.Measurement;
import com.polchlopek.entity.MeasurementData;
import com.polchlopek.entity.Person;
import com.polchlopek.service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/measurement")
public class MeasurementController {

	@Autowired
	private AplicationService applicationService;
	
	@RequestMapping("/showMeasurement")
	public String listMeasurements(Model theModel) {
		
		// get Measurement from dao
		List<Measurement> theMeasurements = applicationService.getMeasurements();
		Measurement tmpMeasurement = new Measurement();

		// tutaj mozna zmienic na hash mape z konkretnymi wartosciami
		List<String> availableCategory = applicationService.getCategories();


		// add the measurements to the model
		theModel.addAttribute("measurements", theMeasurements);
		theModel.addAttribute("wantedMeasurement", tmpMeasurement);
		theModel.addAttribute("availableCategory", availableCategory);

		return "list-measurement-content";
	}

	@RequestMapping("/searchMeasurements")
	public String searchMeasurements(Model theModel,
									 @ModelAttribute("wantedMeasurement") Measurement tmpMwas,
									 @ModelAttribute("availableCategory") LinkedList<String> availableCategory,
									 @ModelAttribute("measurements") LinkedList<Measurement> theMeasurements) {



		return "list-measurement-content";
	}
	
	@RequestMapping("/showGraph")
	public String showGraph(@RequestParam("measurementId") int theId, 
							Model theModel) {
		
		// get the customer from our service
		List<MeasurementData> theMeasurement = applicationService.getArrayMeassurement(theId);
		
 		// set measurement as a model attribute to show graph
 		theModel.addAttribute("actualMeasurement", theMeasurement);
 		
		// send to graph
		return "graph";
	}

	
}





