package com.polchlopek.dao;

import com.polchlopek.entity.MeasurementData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementArrayDAO {
	
	public List<MeasurementData> getArrayMeassurement(int theId);
	
	
	
}
