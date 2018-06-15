package com.polchlopek.entity;

import javax.persistence.*;

@Entity
@Table(name = "measurement_analysis")
public class MeasurementAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "minimum")
    private double minimum;

    @Column(name = "maximum")
    private double maximum;

    @Column(name = "average")
    private double average;

    @Column(name = "variance")
    private double variance;

    @Column(name = "standard_deviation")
    private double standardDeviation;

    public MeasurementAnalysis() {

    }

    public MeasurementAnalysis(double minimum, double maximum, double average,
                               double variance, double standardDeviation) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.average = average;
        this.variance = variance;
        this.standardDeviation = standardDeviation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }
}
