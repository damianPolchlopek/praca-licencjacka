package com.polchlopek.entity;

import com.polchlopek.classToVal.PersonToValAdd;
import com.polchlopek.classToVal.PersonToValUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="users")
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="username")
	private String nickName;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone")
	private Integer phone;

	@Column(name="enabled")
	private boolean enabled;
	
	@OneToMany(fetch=FetchType.LAZY, 
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH,})
	@JoinColumn(name="users_id_login")
	private List<Login> logins;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade= {CascadeType.ALL})
	@JoinColumn(name="users_id_meas")
	private List<Measurement> measurements;

	@OneToMany(fetch=FetchType.EAGER,
			   cascade= {CascadeType.ALL})
	@JoinColumn(name="user_id")
	private List<Authorities> authorities;
	
	public Person() {
		
	}
	
	public Person(PersonToValUpdate ptv) {
		this.id = ptv.getId();
		this.nickName = ptv.getNickName();
		this.firstName = ptv.getFirstName();
		this.lastName = ptv.getLastName();
		this.email = ptv.getEmail();
		this.password = ptv.getPassword();
		this.phone = ptv.getPhone();
		this.enabled = ptv.getEnabled();
	}
	
	public Person(PersonToValAdd ptv) {
		this.nickName = ptv.getNickName();
		this.firstName = ptv.getFirstName();
		this.lastName = ptv.getLastName();
		this.email = ptv.getEmail();
		this.password = ptv.getPassword();
		this.phone = ptv.getPhone();
		this.enabled = ptv.getEnabled();

		List<Authorities> tmpAuthorities = new LinkedList<Authorities>();
		for(String tmp: ptv.getAuthorities()) {
			tmpAuthorities.add(new Authorities(ptv.getNickName(), tmp));
		}

		this.authorities = tmpAuthorities;
	}

	public List<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authorities> authorities) {
		this.authorities = authorities;
	}

	public List<Login> getLogins() {
		return logins;
	}
	
	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public void setLogins(List<Login> logins) {
		this.logins = logins;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String toString() {
		return "Person orginal [firstName=" + firstName +", id= " + id + ", lastName=" + lastName +
				", email =" + email + ", phone=" + phone + ", autho=" + authorities + " ]";
	}
	
	// add convience method
	public void addLogin(Login theLogin) {
		
		if(logins == null) {
			logins = new ArrayList<>();
		}
		
		logins.add(theLogin);
		theLogin.setPersonId(this);		
	}
	
	public void addMeasurement(Measurement theMeasurement) {
			
		if(measurements == null) {
			measurements = new ArrayList<>();
		}
		
		measurements.add(theMeasurement);
	}

}
