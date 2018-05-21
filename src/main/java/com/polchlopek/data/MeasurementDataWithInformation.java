package com.polchlopek.data;

import com.polchlopek.entity.MeasurementData;

import java.util.ArrayList;

public class MeasurementDataWithInformation {

    private ArrayList<MeasurementData> measurementData;

    private String description;

    private String category;

    private String descriptionAxisX;

    private String descriptionAxisY;

    public MeasurementDataWithInformation(ArrayList<MeasurementData> measurementData, String description,
                                          String category, String descriptionAxisX, String descriptionAxisY) {
        this.measurementData = measurementData;
        this.description = description;
        this.category = category;
        this.descriptionAxisX = descriptionAxisX;
        this.descriptionAxisY = descriptionAxisY;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<MeasurementData> getMeasurementData() {
        return measurementData;
    }

    public void setMeasurementData(ArrayList<MeasurementData> measurementData) {
        this.measurementData = measurementData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionAxisX() {
        return descriptionAxisX;
    }

    public void setDescriptionAxisX(String descriptionAxisX) {
        this.descriptionAxisX = descriptionAxisX;
    }

    public String getDescriptionAxisY() {
        return descriptionAxisY;
    }

    public void setDescriptionAxisY(String descriptionAxisY) {
        this.descriptionAxisY = descriptionAxisY;
    }
}
