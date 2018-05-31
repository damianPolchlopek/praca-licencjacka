package com.polchlopek.service;

import com.polchlopek.entity.MeasurementData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignalAnalysisServiceImpl implements SignalAnalysisService {

	public float calculateAverage(List<MeasurementData> measurements) {
		float sum = 0;
		int size = measurements.size();

		for (MeasurementData tmpNode : measurements) {
			sum += tmpNode.getNodeY();
		}

		return sum / size;
	}

	@Override
	public double calculateStandardDeviation(List<MeasurementData> measurements) {
		return Math.sqrt(calculateVariance(measurements));
	}

	@Override
	public double calculateVariance(List<MeasurementData> measurements) {

		double average = calculateAverage(measurements);
		double sum = 0;
		double tmpNodeY;
		double differenceAverageTmpValue;

		for (int i = 0; i < measurements.size()-1; ++i){
			tmpNodeY = measurements.get(i).getNodeY();
			differenceAverageTmpValue = tmpNodeY - average;
			sum +=  Math.pow(differenceAverageTmpValue, 2);
		}

		return sum;
	}

	@Override
	public List<MeasurementData> calculateFFT(List<MeasurementData> measurements) {
		return null;
	}

}
