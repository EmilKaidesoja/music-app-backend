package com.project.musicapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.musicapp.domain.*;
import com.project.musicapp.service.AuthenticationService;

@RestController
public class WebController {
	@Autowired
	private UserRepository urepository;
	@Autowired
	private FavoriteSongRepository fsrepository;
	@Autowired
	private PrevSearchRepository psrepository;

	// fetch user based on user name
	@RequestMapping(value = "/users/{token}", method = RequestMethod.GET)
	public @ResponseBody User GetUsersByIdRest(@PathVariable("token") String token) {
		String username = AuthenticationService.parseToken(token);
		return urepository.findByUsername(username);
	}
	
	// fetch previous searches based on user name
	@RequestMapping(value = "/prevSearches/{token}", method = RequestMethod.GET)
	public @ResponseBody List<PrevSearch> GetPrevSearchesById(@PathVariable("token") String token) {
		String username = AuthenticationService.parseToken(token);
		User user = urepository.findByUsername(username);
		return psrepository.findByUserId(user.getId());
	}
	// fetch favorite songs based on user name
	@RequestMapping(value = "/favoriteSongs/{token}", method = RequestMethod.GET)
	public @ResponseBody List<FavoriteSong> GetfavoriteSongsById(@PathVariable("token") String token) {
		String username = AuthenticationService.parseToken(token);
		User user = urepository.findByUsername(username);
		return fsrepository.findByUserId(user.getId());
	}
	// add search to the database
	@RequestMapping(value = "/postSearch/{token}", method = RequestMethod.POST)
	public @ResponseBody void PostSearch(@RequestBody PrevSearch prevSearch, @PathVariable("token") String token) {
		String username = AuthenticationService.parseToken(token);
		Long userId = urepository.findByUsername(username).getId();
		prevSearch.setUserId(userId);
		psrepository.save(prevSearch);
	}
	
	// add favorite song to the database
	@RequestMapping(value = "/addFavoriteSong/{token}", method = RequestMethod.POST)
	public @ResponseBody void PostFavoriteSong(@RequestBody FavoriteSong favoriteSong, @PathVariable("token") String token) {
		String username = AuthenticationService.parseToken(token);
		Long userId = urepository.findByUsername(username).getId();
		favoriteSong.setUserId(userId);
		fsrepository.save(favoriteSong);
	}


}
