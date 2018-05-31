package com.polchlopek.dao;

import com.polchlopek.entity.MeasurementAnalysis;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementAnalysisDAO {

    MeasurementAnalysis getMeasurementAnalysis(int theId);

}
