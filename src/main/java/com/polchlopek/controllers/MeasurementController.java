package com.polchlopek.controllers;


import com.polchlopek.data.DataMeasurement;
import com.polchlopek.entity.Measurement;
import com.polchlopek.entity.MeasurementData;
import com.polchlopek.service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

		// tutaj mozna zmienic na hash mape z konkretnymi wartosciami
		List<String> availableCategory = applicationService.getCategories();

		DataMeasurement dataMeasurement = new DataMeasurement();

		// add the measurements to the model
		theModel.addAttribute("measurements", theMeasurements);
		theModel.addAttribute("availableCategory", availableCategory);
		theModel.addAttribute("dataMeasurement", dataMeasurement);

		return "list-measurement-content";
	}

	@RequestMapping("/searchMeasurements")
	public String searchMeasurements(Model theModel,
									 @ModelAttribute("dataMeasurement") DataMeasurement dataMeasurement) {

		// tutaj mozna zmienic na hash mape z konkretnymi wartosciami
		List<String> availableCategory = applicationService.getCategories();


		//List<Measurement> theMeasurements = applicationService.getMeasurements();
		List<Measurement> theMeasurements = applicationService.getMeasurements(dataMeasurement);

		// get Measurement from dao
//		List<Measurement> theMeasurements = new LinkedList<>();
//		theMeasurements.add(new Measurement());
//		theMeasurements.add(new Measurement());

		theModel.addAttribute("measurements", theMeasurements);
		theModel.addAttribute("availableCategory", availableCategory);

		System.out.println("Available category z wyszukiwania: " + availableCategory);
		System.out.println("Data measurement: " + dataMeasurement);
		System.out.println("measurement: " + theMeasurements);

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





