package com.polchlopek.data;

public class DataMeasurement {

    private String description;

    private String category;

    public DataMeasurement() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "DataMeasurement{" +
                "description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
