package com.project.musicapp.domain;

public class PasswordChange {
	private String password;

	public PasswordChange() {}
	
	public PasswordChange(String password) {
		super();
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
