package com.polchlopek.service;

import com.polchlopek.entity.MeasurementData;

import java.util.List;


public interface SignalAnalysisService {

	float calculateAverage(List<MeasurementData> measurements);

	double calculateStandardDeviation(List<MeasurementData> measurements);

	double calculateVariance(List<MeasurementData> measurements);

	List<MeasurementData> calculateFFT(List<MeasurementData> measurements);


}
