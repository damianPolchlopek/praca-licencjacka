package com.polchlopek.controllers;

import com.polchlopek.data.DataMeasurement;
import com.polchlopek.data.MeasurementDataWithInformation;
import com.polchlopek.data.MultipleMeasurement;
import com.polchlopek.entity.Measurement;
import com.polchlopek.entity.MeasurementCategory;
import com.polchlopek.entity.MeasurementData;
import com.polchlopek.entity.Person;
import com.polchlopek.service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
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

		List<MeasurementData> theMeasurement = applicationService.getArrayMeassurement(theId);
		String description = applicationService.getDescription(theId);
		String category = applicationService.getCategory(theId);
		String descriptionAxisX = applicationService.getDescriptionAxisX(theId);
		String descriptionAxisY = applicationService.getDescriptionAxisY(theId);
		MeasurementDataWithInformation measurementDataWithInformation =
				new MeasurementDataWithInformation((ArrayList<MeasurementData>) theMeasurement,
						description, category, descriptionAxisX, descriptionAxisY);

 		// set measurement as a model attribute to show graph
 		theModel.addAttribute("actualMeasurement", measurementDataWithInformation);

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
		String descriptionAxisX;
		String descriptionAxisY;

		for (int tmpId: multipleMeasurement.getMeasurementToGraph()){
			tmpMeasData = applicationService.getArrayMeassurement(tmpId);
			description = applicationService.getDescription(tmpId);
			category = applicationService.getCategory(tmpId);
			descriptionAxisX = applicationService.getDescriptionAxisX(tmpId);
			descriptionAxisY = applicationService.getDescriptionAxisY(tmpId);
			measurementDataWithInformation = new MeasurementDataWithInformation((ArrayList<MeasurementData>) tmpMeasData,
					description, category, descriptionAxisX, descriptionAxisY);
			selectedMeasurements.add(measurementDataWithInformation);
		}

		theModel.addAttribute("selectedMeasurements", selectedMeasurements);

		return "multiple-graph";
	}

	@RequestMapping("/addMeasurementPanel")
	public String showAddMeasurementPanel() {

		System.out.println("Dodawanie pomiarow - panel nawigacyjny");

		return "add-measurement-panel";
	}

	@RequestMapping("/addMeasurement")
	public String addMeasurement() {

		System.out.println("D-O-D-A-N-I-E P-O-M-I-A-R-U");


		String description = "pomiar temperatury z czwartku";
		java.util.Date utilDate = new java.util.Date();
		Date sqlDate = new Date(utilDate.getTime());
		Measurement measurementToAdd = new Measurement(sqlDate, description);


		String category = "Cisnienie";
		String descriptionAxisX = "Time [h]";
		String descriptionAxisY = "Preassure [b]";


		MeasurementCategory measurementCategory = applicationService.getMeasurementCategory(category);
		if (measurementCategory == null) {
			measurementCategory = new MeasurementCategory(category,
					descriptionAxisX, descriptionAxisY);
		}
		measurementToAdd.setCategory(measurementCategory);


		// dodawanie przebiegow
		for (int i = 1; i < 5; ++i){
			measurementToAdd.addNode(new MeasurementData(i, i+1));
		}


		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Person person = applicationService.getPerson(username);
		person.addMeasurement(measurementToAdd);
		applicationService.savePerson(person);

		return "add-measurement";
	}


}





