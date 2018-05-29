package com.polchlopek.dao;

import com.polchlopek.entity.MeasurementCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementCategoryDAO {

    List<String> getCategories();

    MeasurementCategory getMeasurementCategory(String category);

    String getTypeGraph(int tmpId);
}
