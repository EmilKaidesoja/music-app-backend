package com.project.musicapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.musicapp.domain.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RepositoryTest {
	
	@Autowired
	FavoriteSongRepository favRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PrevSearchRepository prevRepository;
	
	@Test
	public void findbyUserIdShouldReturnFavoriteSongs() {
		List<FavoriteSong> favSongs = favRepository.findByUserId(Long.valueOf(1));
		assertThat(favSongs).isNotEmpty();
		assertThat(favSongs.get(0).getUserId()).isEqualTo(Long.valueOf(1));
	}
	@Test
	public void createNewFavoriteSongAndDelete() {
		FavoriteSong favSong = new FavoriteSong(Long.valueOf(100),Long.valueOf(1), "Michael Jackson", "Beat it");
		favRepository.save(favSong);
		assertThat(favSong.getId()).isNotNull();
		
		Optional<FavoriteSong> checkFavSong = favRepository.findById(Long.valueOf(100));
		favRepository.deleteById(checkFavSong.get().getId());
		Optional<FavoriteSong> editedFavSong = favRepository.findById(Long.valueOf(100));
		Optional.ofNullable(editedFavSong);
		assertThat(editedFavSong).isEmpty();
	}
	
	@Test
	public void findbyUserIdShouldReturnPreviousSearches() {
		List<PrevSearch> prevSearches = prevRepository.findByUserId(Long.valueOf(1));
		assertThat(prevSearches).isNotEmpty();
		assertThat(prevSearches.get(0).getUserId()).isEqualTo(Long.valueOf(1));
	}
	@Test
	public void createNewPreviousSearchAndDelete() {
		PrevSearch prevSearch = new PrevSearch("Rihanna", Long.valueOf(1));
		prevRepository.save(prevSearch);
		assertThat(prevSearch.getId()).isNotNull();
		
		Optional<PrevSearch> checkPrevSearch = prevRepository.findById(prevSearch.getId());
		prevRepository.deleteById(checkPrevSearch.get().getId());
		Optional<PrevSearch> editedPrevSearch = prevRepository.findById(prevSearch.getId());
		Optional.ofNullable(editedPrevSearch);
		assertThat(editedPrevSearch).isEmpty();
	}
	
	@Test
	public void findbyUsernameShouldReturnUser() {
		User user = userRepository.findByUsername("user");
		assertThat(user.getUsername()).isEqualTo("user");
	}
	@Test
	public void createNewUserAndDelete() {
		User user = new User("firstname", "lastname", "testUser@musicapp.com", "TestUser", "$2a$10$Nup.FsJ6XeYoSzXeqUaDHu5gacmUKo0pqKeZKFckzNPOsxQfWQBIW", "USER");
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
		
		User checkUser = userRepository.findByUsername("TestUser");
		userRepository.delete(checkUser);
		User editedUser = userRepository.findByUsername("TestUser");
		assertThat(editedUser).isNull();
	}
}
