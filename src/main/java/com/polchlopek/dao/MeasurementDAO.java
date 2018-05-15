package com.polchlopek.dao;

import com.polchlopek.data.DataMeasurement;
import com.polchlopek.entity.Measurement;
import com.polchlopek.entity.MeasurementData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementDAO {
	
	public List<Measurement> getMeasurements();

	public Measurement getMeasurement(int theId);

	public List<MeasurementData> getMeasurementData();

    List<Measurement> getMeasurements(DataMeasurement dataMeasurement);
}
