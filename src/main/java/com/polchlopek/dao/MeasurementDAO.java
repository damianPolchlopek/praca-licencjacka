package com.polchlopek.dao;

import com.polchlopek.dto.DataMeasurement;
import com.polchlopek.entity.Measurement;
import com.polchlopek.entity.MeasurementData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementDAO {
	
	List<Measurement> getMeasurements();

	Measurement getMeasurement(int theId);

	List<MeasurementData> getMeasurementData();

    List<Measurement> getMeasurements(DataMeasurement dataMeasurement);

    String getDescription(int tmpId);

	String getCategory(int tmpId);

    String getDescriptionAxisX(int tmpId);

	String getDescriptionAxisY(int tmpId);

    void saveMeasurement(Measurement measurement);

    void deleteMeasurement(int theId);
}
