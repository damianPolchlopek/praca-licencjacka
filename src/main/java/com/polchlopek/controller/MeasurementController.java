package com.polchlopek.controller;

import com.polchlopek.dto.*;
import com.polchlopek.entity.*;
import com.polchlopek.service.AplicationService;
import com.polchlopek.service.SignalAnalysisService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/measurement")
@SessionAttributes({"measurementToAdd", "dataFourier"})
public class MeasurementController {

	@Autowired
	private AplicationService applicationService;

	@Autowired
    private SignalAnalysisService signalAnalysisService;


	@RequestMapping("/showMeasurement")
	public String listMeasurements(Model theModel) {

		List<Measurement> theMeasurements = applicationService.getMeasurements();
		List<String> availableCategory = applicationService.getCategories();
		DataMeasurement dataMeasurement = new DataMeasurement();
		MultipleMeasurement multipleMeasurement = new MultipleMeasurement();

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

		List<String> availableCategory = applicationService.getCategories();
		List<Measurement> theMeasurements = applicationService.getMeasurements(dataMeasurement);

		theModel.addAttribute("measurements", theMeasurements);
		theModel.addAttribute("availableCategory", availableCategory);

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
		String typeGraph = applicationService.getTypeGraph(theId);

		MeasurementDataWithInformation measurementDataWithInformation =
				new MeasurementDataWithInformation((ArrayList<MeasurementData>) theMeasurement,
						description, category, descriptionAxisX, descriptionAxisY, typeGraph);

 		theModel.addAttribute("actualMeasurement", measurementDataWithInformation);

        MeasurementAnalysis measurementAnalysis = applicationService.getMeasurementAnalysis(theId);
        theModel.addAttribute("measurementAnalysis", measurementAnalysis);

		return "single-graph";
	}

	@RequestMapping("/showFourier")
	public String showFourier(@RequestParam("measurementId") int theId,
							  Model theModel){

		Measurement measurementToCalculate = applicationService.getMeasurement(theId);
		List<MeasurementData> dataToFFT = measurementToCalculate.getNodes();
		List<MeasurementData> dataFFT = signalAnalysisService.calculateFFT(dataToFFT);

		FourierDescription fourierDescription = new FourierDescription("line",
														"line", "column");

		theModel.addAttribute("dataFourier", dataFFT);
		theModel.addAttribute("fourierDescription", fourierDescription);

		return "fourier-graph";
	}

	@RequestMapping("/changeFourierGraph")
	public String changeFourierGraph(@ModelAttribute("fourierDescription") FourierDescription fourierDescription,
									 Model theModel){

		theModel.addAttribute("fourierDescription", fourierDescription);

		return "fourier-graph";
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
		String typeGraph;

		for (int tmpId: multipleMeasurement.getMeasurementToGraph()){
			tmpMeasData = applicationService.getArrayMeassurement(tmpId);
			description = applicationService.getDescription(tmpId);
			category = applicationService.getCategory(tmpId);
			descriptionAxisX = applicationService.getDescriptionAxisX(tmpId);
			descriptionAxisY = applicationService.getDescriptionAxisY(tmpId);
			typeGraph = applicationService.getTypeGraph(tmpId);

			measurementDataWithInformation = new MeasurementDataWithInformation((ArrayList<MeasurementData>) tmpMeasData,
					description, category, descriptionAxisX, descriptionAxisY, typeGraph);
			selectedMeasurements.add(measurementDataWithInformation);
		}

		if (selectedMeasurements.isEmpty()) {
			return "multiple-graph-error";
		}
		else{
			theModel.addAttribute("selectedMeasurements", selectedMeasurements);
			return "multiple-graph";
		}
	}

	@RequestMapping("/addMeasurementPanel")
	public String showAddMeasurementPanel(Model theModel) {

		FileMeasurementData fileMeasurementData = new FileMeasurementData();
		theModel.addAttribute("newMeasurement", fileMeasurementData);

		return "add-measurement-panel";
	}

	@RequestMapping(value = "/addMeasurement", method = RequestMethod.POST)
	public String addMeasurement(Model theModel,
	        @ModelAttribute("newMeasurement") FileMeasurementData file) {

        byte[] bytes = file.getFile().getBytes();
        String contentUploadedFile = new String(bytes);

        final String description_pattern = "Description: ([\\w|\\W]+)" +
                "Category: ([\\w|\\W]+)" +
				"Type: ([\\w|\\W]+)" +
                "Description axis x: ([\\w|\\W]+)" +
                "Description axis y: ([\\w|\\W]+)" +
                "Data:([\\w|\\W]+)";

        final String data_pattern = "(-?\\d*.?\\d+), (-?\\d*.?\\d+)";

        Pattern r = Pattern.compile(description_pattern);
        Matcher m = r.matcher(contentUploadedFile);

        String description ;
        String category ;
		String typeGraph;
        String descriptionAxisX;
        String descriptionAxisY ;
        String data ;

        if (m.find()){
            try{
                description = m.group(1).trim();
                category = m.group(2).trim();
                typeGraph = m.group(3).trim();
                descriptionAxisX = m.group(4).trim();
                descriptionAxisY = m.group(5).trim();
                data = m.group(6);
            }
            catch(Exception e){
                return "add-measurement-panel";
            }
        }
        else {
            return "redirect:/help";
        }

        java.util.Date utilDate = new java.util.Date();
        Date sqlDate = new Date(utilDate.getTime());
        Measurement measurementToAdd = new Measurement(sqlDate, description);

		MeasurementCategory measurementCategory = applicationService.getMeasurementCategory(category);
		if (measurementCategory == null) {
			measurementCategory = new MeasurementCategory(category,
					descriptionAxisX, descriptionAxisY, typeGraph);
		}
		measurementToAdd.setCategory(measurementCategory);

        Pattern rData = Pattern.compile(data_pattern);
        Matcher mData = rData.matcher(data);

        // dodanie wektora danych
        while (mData.find()) {
            System.out.println(mData.group(1) + ", " + mData.group(2));
            measurementToAdd.addNode(new MeasurementData(Float.parseFloat(mData.group(1)),
                                            Float.parseFloat(mData.group(2))));
        }

        // analiza sygnalu
        double maximum = Collections.max(measurementToAdd.getNodes()).getNodeY();
        double minimum = Collections.min(measurementToAdd.getNodes()).getNodeY();
        double average = signalAnalysisService.calculateAverage(measurementToAdd.getNodes());
        double variance = signalAnalysisService.calculateVariance(measurementToAdd.getNodes());
        double standardDeviation = signalAnalysisService
                .calculateStandardDeviation(measurementToAdd.getNodes());

        MeasurementAnalysis measurementAnalysis = new MeasurementAnalysis(
				maximum, minimum, average, variance, standardDeviation);

		measurementToAdd.setMeasurementAnalysis(measurementAnalysis);

        // ustawienie osoby
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Person person = applicationService.getPerson(username);
		measurementToAdd.setPersonId(person);

		// sprawdzenie pooprawnosci wprowadzanych danych
		for (MeasurementData tmpNode : measurementToAdd.getNodes()){

			if (tmpNode.getNodeY() > 2*average){
				theModel.addAttribute("measurementToAdd", measurementToAdd);
				return "measurement-warning";
			}
		}

		applicationService.saveMeasurement(measurementToAdd);

		return "add-measurement";
	}

	@RequestMapping(value = "/addMeasurementWarning")
	public String addMeasurementWarning(@ModelAttribute("measurementToAdd") Measurement measurementToAdd){

		applicationService.saveMeasurement(measurementToAdd);
		return "add-measurement";
	}

	@RequestMapping("/delete")
	public String deleteMeasurement(@RequestParam("measurementId") int theId) {

		applicationService.deleteMeasurement(theId);
		return "redirect:/measurement/showMeasurement";
	}

}
