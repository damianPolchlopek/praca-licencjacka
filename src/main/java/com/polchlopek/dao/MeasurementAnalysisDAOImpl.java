package com.polchlopek.dao;

import com.polchlopek.entity.Measurement;
import com.polchlopek.entity.MeasurementAnalysis;
import com.polchlopek.entity.MeasurementCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MeasurementAnalysisDAOImpl implements MeasurementAnalysisDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public MeasurementAnalysis getMeasurementAnalysis(int theId) {

        Session currentSession = sessionFactory.getCurrentSession();
        Measurement measurement = currentSession.get(Measurement.class, theId);
        return currentSession.get(MeasurementAnalysis.class, measurement.getMeasurementAnalysis().getId());
    }
}
