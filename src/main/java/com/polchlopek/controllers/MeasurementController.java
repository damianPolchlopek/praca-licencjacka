package com.polchlopek.controllers;


import com.polchlopek.data.DataMeasurement;
import com.polchlopek.data.MeasurementDataWithInformation;
import com.polchlopek.data.MultipleMeasurement;
import com.polchlopek.entity.Measurement;
import com.polchlopek.entity.MeasurementData;
import com.polchlopek.service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
		MultipleMeasurement multipleMeasurement = new MultipleMeasurement();

		// add the measurements to the model
		theModel.addAttribute("measurements", theMeasurements);
		theModel.addAttribute("availableCategory", availableCategory);
		theModel.addAttribute("dataMeasurement", dataMeasurement);
		theModel.addAttribute("multipleMeasurement", multipleMeasurement);

		return "list-measurement-content";
	}

	@RequestMapping("/searchMeasurements")
	public String searchMeasurements(Model theModel,
									 @ModelAttribute("dataMeasurement") DataMeasurement dataMeasurement,
									 @ModelAttribute("multipleMeasurement") MultipleMeasurement multipleMeasurement) {

		// tutaj mozna zmienic na hash mape z konkretnymi wartosciami
		List<String> availableCategory = applicationService.getCategories();

		//List<Measurement> theMeasurements = applicationService.getMeasurements();
		List<Measurement> theMeasurements = applicationService.getMeasurements(dataMeasurement);

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

	@RequestMapping("/showMultipleGraph")
	public String showMultipleGraph(Model theModel,
									@ModelAttribute("multipleMeasurement") MultipleMeasurement multipleMeasurement) {

		List<MeasurementDataWithInformation> selectedMeasurements = new ArrayList<>();
		List<MeasurementData> tmpMeasData;
		MeasurementDataWithInformation measurementDataWithInformation;
		String description;
		String category;

		for (int tmpId: multipleMeasurement.getMeasurementToGraph()){
			tmpMeasData = applicationService.getArrayMeassurement(tmpId);
			description = applicationService.getDescription(tmpId);
			category = applicationService.getCategory(tmpId);
			measurementDataWithInformation = new MeasurementDataWithInformation((ArrayList<MeasurementData>) tmpMeasData,
					description, category);
			selectedMeasurements.add(measurementDataWithInformation);
		}

		theModel.addAttribute("selectedMeasurements", selectedMeasurements);

		return "multiple-graph";
	}


	@RequestMapping("/addMeasurement")
	public String addMeasurement() {


		// send to graph
		return "add-measurement";
	}


}





