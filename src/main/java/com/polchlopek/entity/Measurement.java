package com.polchlopek.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="measurement")
public class Measurement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date_measurement")
	private Date dateMeasurement;
	
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH,})
	@JoinColumn(name="person_id_meas")
	private Person personId;
	
	public Measurement() {
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateMeasurement() {
		return dateMeasurement;
	}

	public void setDateMeasurement(Date dateMeasurement) {
		this.dateMeasurement = dateMeasurement;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Person getPersonId() {
		return personId;
	}

	public void setPersonId(Person personId) {
		this.personId = personId;
	}
	
	public String toString() {
		return "Meas [id=" + id + ", dateMeasurement=" + dateMeasurement + ", description= " + description + ", personId=" + personId + "]";
	}
	
}
