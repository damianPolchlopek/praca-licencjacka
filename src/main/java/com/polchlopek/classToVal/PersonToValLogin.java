package com.polchlopek.classToVal;

import javax.validation.constraints.Size;

public class PersonToValLogin {
	
	@Size(min=1, max=15, message="you must entry nickname")
	private String nickName;
	
	@Size(min=1, max=15, message="you must entry password")
	private String password;

	public PersonToValLogin() {
		
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return "Person [nickName=" + nickName  + "]";
	}

}
