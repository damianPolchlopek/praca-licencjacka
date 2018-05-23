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
	private int nodeX;
	
	@Column(name="node_y")
	private int nodeY;
	
	public MeasurementData() {
		
	}

	public MeasurementData(int nodeX, int nodeY) {
		this.nodeX = nodeX;
		this.nodeY = nodeY;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNodeX() {
		return nodeX;
	}

	public void setNodeX(int nodeX) {
		this.nodeX = nodeX;
	}

	public int getNodeY() {
		return nodeY;
	}

	public void setNodeY(int nodeY) {
		this.nodeY = nodeY;
	}

	public String toString() {
		return "MeasData [id=" + id + ", nodeX=" + nodeX + ", nodeY= " + nodeY + "]";
	}
	
}
