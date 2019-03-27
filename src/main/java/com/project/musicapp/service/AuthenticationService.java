package com.project.musicapp.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

public class AuthenticationService {
	public static final long EXPIRATIONTIME = 864_000_00;
	public static final String SIGNINGKEY = "Secretkey";
	public static final String PREFIX = "Bearer";
	
	// Add token to the response
	// the token is the users username encrypted, it is used for authentication for all incoming requests
	static public void addToken(HttpServletResponse response, String username) {
		String JwtToken = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SIGNINGKEY).compact();
		response.addHeader("Authorization", PREFIX + " " + JwtToken);
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
	}

	// check the received requests authorization token
	static public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");	
		if (token != null) {		
			String user = Jwts.parser().setSigningKey(SIGNINGKEY).parseClaimsJws(token.replace(PREFIX, "")).getBody()
					.getSubject();
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, emptyList());
			}
		}
		return null;
	}
	public static String parseToken(String token) {
		final String SIGNINGKEY = "Secretkey";
		final String PREFIX = "Bearer";
		String username = Jwts.parser().setSigningKey(SIGNINGKEY).parseClaimsJws(token.replace(PREFIX, "")).getBody()
				.getSubject();
		return username;
	}

	public static String HashPassword(String rawPassword) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(rawPassword);
	}
}
