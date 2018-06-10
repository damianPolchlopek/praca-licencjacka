package com.polchlopek.dto;

public class FourierDescription {

    private String typeAxisX;

    private String typeAxisY;

    private String typeGraph;

    private boolean zoom;

    public FourierDescription() {
    }

    public FourierDescription(String typeAxisX, String typeAxisY, String typeGraph) {
        this.typeAxisX = typeAxisX;
        this.typeAxisY = typeAxisY;
        this.typeGraph = typeGraph;
        this.zoom = false;
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

    public boolean isZoom() {
        return zoom;
    }

    public void setZoom(boolean zoom) {
        this.zoom = zoom;
    }

    @Override
    public String toString() {
        return "FourierDescription{" +
                "typeAxisX='" + typeAxisX + '\'' +
                ", typeAxisY='" + typeAxisY + '\'' +
                ", typeGraph='" + typeGraph + '\'' +
                '}';
    }
}
