package com.polchlopek.dto;

public class FourierDescription {

    private String typeAxisX;

    private String typeAxisY;

    private String typeGraph;

    public FourierDescription() {
    }

    public FourierDescription(String typeAxisY, String typeGraph) {
        this.typeAxisY = typeAxisY;
        this.typeGraph = typeGraph;
    }

    public String getTypeAxisX() {
        return typeAxisX;
    }

    public void setTypeAxisX(String typeAxisX) {
        this.typeAxisX = typeAxisX;
    }

    public String getTypeAxisY() {
        return typeAxisY;
    }

    public void setTypeAxisY(String typeAxisY) {
        this.typeAxisY = typeAxisY;
    }

    public String getTypeGraph() {
        return typeGraph;
    }

    public void setTypeGraph(String typeGraph) {
        this.typeGraph = typeGraph;
    }

}
