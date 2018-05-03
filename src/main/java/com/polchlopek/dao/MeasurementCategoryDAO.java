package com.polchlopek.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementCategoryDAO {

    List<String> getCategories();

}
