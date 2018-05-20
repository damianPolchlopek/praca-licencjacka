package com.polchlopek.data;

import java.util.Arrays;

public class MultipleMeasurement {

    private String[] measurementToGraph;

    public String[] getMeasurementToGraph() {
        return measurementToGraph;
    }

    public void setMeasurementToGraph(String[] measurementToGraph) {
        this.measurementToGraph = measurementToGraph;
    }

    @Override
    public String toString() {
        return "MultipleMeasurement{" +
                "measurementToGraph=" + Arrays.toString(measurementToGraph) +
                '}';
    }
}
