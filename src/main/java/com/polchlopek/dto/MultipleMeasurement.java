package com.polchlopek.dto;

import java.util.Arrays;

public class MultipleMeasurement {

    private int[] measurementToGraph;

    public int[] getMeasurementToGraph() {
        return measurementToGraph;
    }

    public void setMeasurementToGraph(int[] measurementToGraph) {
        this.measurementToGraph = measurementToGraph;
    }

    public String toString() {
        return "MultipleMeasurement{" +
                "measurementToGraph=" + Arrays.toString(measurementToGraph) +
                '}';
    }
}

