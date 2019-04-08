package com.project.musicapp.domain;

public class SpotifyAuthentication {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private String expires_in;
	private String scope;
	
	public SpotifyAuthentication() {}
	
	public SpotifyAuthentication(String authenticationToken, String contentType, String expirationTime,
			String refreshToken, String scope) {
		super();
		this.access_token = authenticationToken;
		this.token_type = contentType;
		this.refresh_token = expirationTime;
		this.expires_in = refreshToken;
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "SpotifyAuthentication [access_token=" + access_token + ", token_type=" + token_type + ", refresh_token=" + refresh_token
				+ ", expires_in=" + expires_in + ", scope="+ scope +"]";
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}	
	
}
