package com.polchlopek.service;

import com.polchlopek.entity.MeasurementData;
import com.polchlopek.signalAnalysisAlgorithm.Complex;
import com.polchlopek.signalAnalysisAlgorithm.FFT;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

	    double Ts = measurements.get(1).getNodeX() -
                        measurements.get(0).getNodeX();
	    double fs = 1/Ts;

		Complex[] x = new Complex[measurements.size()];

		for (int i = 0; i < measurements.size(); ++i){
			x[i] =  new Complex(measurements.get(i).getNodeY(), 0);
		}

		Complex[] result = FFT.fft(x);
		ArrayList<MeasurementData> mainResult = new ArrayList<>();

		double sizeAxisX = result.length/2+1;
		for (int i = 0; i < sizeAxisX; ++i){
			mainResult.add(new MeasurementData((float) (fs*i/result.length), Math.abs((float)result[i].getRe())));
		}

		return mainResult;
	}

}
