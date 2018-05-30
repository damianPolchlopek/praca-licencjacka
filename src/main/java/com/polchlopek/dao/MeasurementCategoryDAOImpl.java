package com.polchlopek.dao;

import com.polchlopek.entity.Measurement;
import com.polchlopek.entity.MeasurementCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MeasurementCategoryDAOImpl implements  MeasurementCategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public List<String> getCategories() {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<String> theQuery = currentSession.createQuery("select cate.category " +
                "from MeasurementCategory cate");

        return theQuery.getResultList();
    }

    public MeasurementCategory getMeasurementCategory(String category) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<MeasurementCategory> theQuery =
                currentSession.createQuery("from MeasurementCategory  where category=:categoryParam");
        theQuery.setParameter("categoryParam", category);
        List<MeasurementCategory> measurementCategory = theQuery.getResultList();

        if (measurementCategory.isEmpty()){
            return null;
        }
        else{
            return measurementCategory.get(0);
        }
    }
    
    public String getTypeGraph(int tmpId) {

        Session currentSession = sessionFactory.getCurrentSession();
        Measurement measurement = currentSession.get(Measurement.class, tmpId);

        MeasurementCategory measurementCategory
                = currentSession.get(MeasurementCategory.class, measurement.getCategory().getId());

        if (measurementCategory == null){
            return null;
        }
        else{
            return measurementCategory.getTypeGraph();
        }

    }
}
