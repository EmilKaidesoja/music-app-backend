package com.project.musicapp.web;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.musicapp.domain.SpotifyAuthentication;
import com.project.musicapp.domain.UserRepository;
import com.project.musicapp.service.AuthenticationService;

@Controller
public class SpotifyController {
	
	private final String CLIENT_ID = "3700d2c69a8248ff9297c02f70a25631";
	private final String REDIRECT_URI = "https://musicapp-backend.me/musicapp/spotify/callback";
	private final String CLIENT_SECRET = "469360b9c8f34bbb9b8eab570ca33443";
	private final String FRONTEND_URL = "https://my-musicapp.me";
	private final String SCOPES = "streaming%20user-read-birthdate%20user-read-email%20user-read-private%20user-modify-playback-state%20user-read-currently-playing";

	@Autowired
	UserRepository userRepository;

	// Receive the connection request from the front end and redirects it to Spotify
	// The token check returns the user if its false 
	// aka if the user is not signed in
	@RequestMapping(value = "/spotify/connect/{token}")
	public String connect(@PathVariable("token") String token) {
		String username = AuthenticationService.parseToken(token);
		if (userRepository.findByUsername(username) == null) {
			return "redirect:" + FRONTEND_URL;
		}

		return "redirect:https://accounts.spotify.com/authorize?response_type=code&client_id=" + CLIENT_ID
				+ "&redirect_uri=" + REDIRECT_URI + "&scope=" + SCOPES;
	}

	// Receive the callback from the initial Spotify authentication
	// Next it sends post method for the actual Authentication token, with the
	// authorization token
	@RequestMapping(value = "/spotify/callback")
	public String callback(@RequestParam("code") String AuthorizationToken) {
		final String URI = "https://accounts.spotify.com/api/token";

		// Set headers and body for the request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		String ids = CLIENT_ID + ":" + CLIENT_SECRET;
		headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString(ids.getBytes()));
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("grant_type", "authorization_code");
		body.add("code", AuthorizationToken);
		body.add("redirect_uri", REDIRECT_URI);

		// the http request entity
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body,
				headers);
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.exchange(URI, HttpMethod.POST, request, String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			SpotifyAuthentication spotifyAuthentication = objectMapper.readValue(response.getBody().toString(), SpotifyAuthentication.class);
			return "redirect:" + FRONTEND_URL + "/home?spotifyToken=" + spotifyAuthentication.getAccess_token();
			
			// catches the error if the request fails and redirects back to the front end
			// This should never happen
		} catch (HttpClientErrorException e) {
			String status = e.getResponseBodyAsString();
			System.out.println(e + " " + status);
			return "redirect:" + FRONTEND_URL;
		} catch (JsonParseException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		return null;
	}
}
