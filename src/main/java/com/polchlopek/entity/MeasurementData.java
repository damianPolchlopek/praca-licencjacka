package com.polchlopek.entity;


import javax.persistence.*;

@Entity
@Table(name="meas_arrays")
public class MeasurementData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="node_x")
	private float nodeX;
	
	@Column(name="node_y")
	private float nodeY;
	
	public MeasurementData() {
		
	}

	public MeasurementData(float nodeX, float nodeY) {
		this.nodeX = nodeX;
		this.nodeY = nodeY;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getNodeX() {
		return nodeX;
	}

	public void setNodeX(float nodeX) {
		this.nodeX = nodeX;
	}

	public float getNodeY() {
		return nodeY;
	}

	public void setNodeY(float nodeY) {
		this.nodeY = nodeY;
	}

	public String toString() {
		return "MeasData [id=" + id + ", nodeX=" + nodeX + ", nodeY= " + nodeY + "]";
	}
	
}
