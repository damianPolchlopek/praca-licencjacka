package com.polchlopek.controllers;

import com.polchlopek.data.DataMeasurement;
import com.polchlopek.data.FileMeasurementData;
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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public String showAddMeasurementPanel(Model theModel) {

		FileMeasurementData fileMeasurementData = new FileMeasurementData();
		theModel.addAttribute("newMeasurement", fileMeasurementData);

		return "add-measurement-panel";
	}

	@RequestMapping(value = "/addMeasurement", method = RequestMethod.POST)
	public String addMeasurement(@ModelAttribute("newMeasurement") FileMeasurementData file2
								 ) throws IOException {

        byte[] bytes = file2.getFile().getBytes();
        String contentUploadedFile = new String(bytes);

        final String description_pattern = "Description: ([\\w|\\W]+)" +
                "Category: ([\\w|\\W]+)" +
                "Description axis x: ([\\w|\\W]+)" +
                "Description axis y: ([\\w|\\W]+)" +
                "Data:([\\w|\\W]+)";

        final String data_pattern = "(-?\\d+), (-?\\d+)";

        Pattern r = Pattern.compile(description_pattern);
        Matcher m = r.matcher(contentUploadedFile);

        String description = "";
        String category = "";
        String descriptionAxisX = "";
        String descriptionAxisY = "";
        String data = "";

        if (m.find()){
            try{
                description = m.group(1).trim();
                category = m.group(2).trim();
                descriptionAxisX = m.group(3).trim();
                descriptionAxisY = m.group(4).trim();
                data = m.group(5);

                System.out.println("Desc: " + m.group(1) );
                System.out.println("Category: " + m.group(2) );
                System.out.println("Description axis x: " + m.group(3) );
                System.out.println("Description axis y: " + m.group(4) );
                System.out.println("Data: " + m.group(5) );

            }
            catch(Exception e){
                System.out.println("Zly format pliku !!!");
                return "add-measurement-panel";
            }

        }
        else {
            System.out.println("Nie znaleziono - regex");
        }


        java.util.Date utilDate = new java.util.Date();
        Date sqlDate = new Date(utilDate.getTime());
        Measurement measurementToAdd = new Measurement(sqlDate, description);

		MeasurementCategory measurementCategory = applicationService.getMeasurementCategory(category);
		if (measurementCategory == null) {
			measurementCategory = new MeasurementCategory(category,
					descriptionAxisX, descriptionAxisY);
		}
		measurementToAdd.setCategory(measurementCategory);

        Pattern rData = Pattern.compile(data_pattern);
        Matcher mData = rData.matcher(data);

        while (mData.find()) {
            System.out.println(mData.group(1) + ", " + mData.group(2));
            measurementToAdd.addNode(new MeasurementData(Integer.parseInt(mData.group(1)),
                                            Integer.parseInt(mData.group(2))));
        }

		System.out.println("[data] " + measurementToAdd);
		System.out.println("[MEAS] " + measurementToAdd.getCategory());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Person person = applicationService.getPerson(username);
		measurementToAdd.setPersonId(person);
		applicationService.saveMeasurement(measurementToAdd);

		return "add-measurement";
	}



	@RequestMapping("/delete")
	public String deleteMeasurement(@RequestParam("measurementId") int theId) {

		applicationService.deleteMeasurement(theId);

		return "redirect:/measurement/showMeasurement";
	}


}





