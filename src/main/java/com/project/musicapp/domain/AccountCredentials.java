package com.project.musicapp.domain;

public class AccountCredentials {
	private String username, password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AccountCredentials [username=" + username + ", password=" + password + "]";
	}
	
	
}
