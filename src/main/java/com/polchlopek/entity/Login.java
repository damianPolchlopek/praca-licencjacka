package com.polchlopek.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="login")
public class Login {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date_log")
	private Date dateLog;
	
	@Column(name="time_log")
	private Time timeLog;
	
	@Column(name="location_log")
	private String locationLog;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="users_id_login")
	private Person personId;
	
	public Login() {

	}

	public Login(Date dateLog, Time timeLog, String locationLog, Person personId) {
		this.dateLog = dateLog;
		this.timeLog = timeLog;
		this.locationLog = locationLog;
		this.personId = personId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateLog() {
		return dateLog;
	}

	public void setDateLog(Date dateLog) {
		this.dateLog = dateLog;
	}
	
	public Time getTimeLog() {
		return timeLog;
	}

	public void setTimeLog(Time timeLog) {
		this.timeLog = timeLog;
	}

	public String getLocationLog() {
		return locationLog;
	}

	public void setLocationLog(String locationLog) {
		this.locationLog = locationLog;
	}

	public Person getPersonId() {
		return personId;
	}

	public void setPersonId(Person personId) {
		this.personId = personId;
	}
	
	public String toString() {
		return "Login [id=" + id + ", dateLog=" + dateLog + ", locationLog=" + locationLog +
				", personId=" + personId + "]";
	}

}
