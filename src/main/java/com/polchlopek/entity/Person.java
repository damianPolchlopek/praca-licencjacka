package com.polchlopek.entity;

import com.polchlopek.validation.PersonToValAdd;
import com.polchlopek.validation.PersonToValUpdate;

import javax.persistence.*;
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

		List<Authorities> tmpAuthorities = new LinkedList<>();
		for(String tmp: ptv.getAuthorities()) {
			tmpAuthorities.add(new Authorities(ptv.getNickName(), tmp));
		}

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
				", email =" + email + ", phone=" + phone + "]";
	}

}
