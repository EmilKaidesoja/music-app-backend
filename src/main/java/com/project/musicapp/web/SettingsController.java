package com.project.musicapp.web;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.musicapp.domain.*;
import com.project.musicapp.service.AuthenticationService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class SettingsController {
	@Autowired
	private UserRepository urepository;
	@Autowired
	private FavoriteSongRepository fsrepository;
	@Autowired
	private PrevSearchRepository psrepository;

	// Delete the users all favorite songs
	@RequestMapping(value = "/delete/favorites/{token}", method = RequestMethod.DELETE)
	public void deleteFavorites(@PathVariable("token") String token) {
		String username = AuthenticationService.parseToken(token);
		Long userId = urepository.findByUsername(username).getId();
		List<FavoriteSong> songs = fsrepository.findByUserId(userId);
		Iterator<FavoriteSong> it = songs.iterator();
		while (it.hasNext()) {
			fsrepository.deleteById(it.next().getId());
		}
	}

	@RequestMapping(value = "/delete/favorite", method = RequestMethod.DELETE)
	public void DeleteFavoriteSong(@RequestBody FavoriteSong favSong) {
		fsrepository.delete(favSong);
	}

	// delete the users search history
	@RequestMapping(value = "/delete/searches/{token}", method = RequestMethod.DELETE)
	public void deleteHistory(@PathVariable("token") String token) {
		String username = AuthenticationService.parseToken(token);
		Long userId = urepository.findByUsername(username).getId();
		List<PrevSearch> searches = psrepository.findByUserId(userId);
		Iterator<PrevSearch> it = searches.iterator();
		while (it.hasNext()) {
			psrepository.deleteById(it.next().getSearchId());
		}
	}

	// Update user information
	@RequestMapping(value = "/save/user/{token}", method = RequestMethod.PUT)
	public String updateInformation(@RequestBody User newUser, @PathVariable("token") String token) {
		String username = AuthenticationService.parseToken(token);
		User user = urepository.findByUsername(username);
		//check if the user requests a new username and if the new username is available
		if(newUser.getUsername() == username){
			if(urepository.findByUsername(newUser.getUsername()) == null) {
				newUser.setId(user.getId());
				newUser.setPassword(user.getPassword());
				urepository.save(newUser);
			}else {
				return "reserved";
			}
		}else {
			newUser.setId(user.getId());
			newUser.setPassword(user.getPassword());
			urepository.save(newUser);
		}
		// return new token with the new user name
		String newToken = Jwts.builder().setSubject(user.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + AuthenticationService.EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, AuthenticationService.SIGNINGKEY).compact();
		return "Bearer " + newToken;
	}
	
	@RequestMapping(value="/update/password/{token}", method = RequestMethod.PUT)
	public void changePassword(@RequestBody PasswordChange password, @PathVariable("token") String token) {
		String username = AuthenticationService.parseToken(token);
		User user = urepository.findByUsername(username);
		user.setPassword(AuthenticationService.HashPassword(password.getPassword()));
		urepository.save(user);
	}

	// Handles user registration
	@RequestMapping(value = "/register/user", method = RequestMethod.POST)
	public String registerUser(@RequestBody User user) {
		// if this returns a user then the username is already taken
		if (urepository.findByUsername(user.getUsername()) != null) {
			return "reserved";
		} else {
			user.setPassword(AuthenticationService.HashPassword(user.getPassword()));
			urepository.save(user);
			String token = Jwts.builder().setSubject(user.getUsername())
					.setExpiration(new Date(System.currentTimeMillis() + AuthenticationService.EXPIRATIONTIME))
					.signWith(SignatureAlgorithm.HS512, AuthenticationService.SIGNINGKEY).compact();
			return "Bearer " + token;
		}
	}

	@RequestMapping(value = "/delete/user/{token}", method = RequestMethod.DELETE)
	public void deleteAccount(@PathVariable("token") String token) {
		String username = AuthenticationService.parseToken(token);
		urepository.deleteById(urepository.findByUsername(username).getId());
	}
}
