package com.project.musicapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.musicapp.domain.*;

@SpringBootApplication
public class MusicappApplication {
	private static final Logger log = LoggerFactory.getLogger(MusicappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MusicappApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository urepository, FavoriteSongRepository fsrepository,
			PrevSearchRepository psrepository) {
		return (args) -> {
			log.info("Save some dummy data");
			User user = new User("firstname", "lastname", "admin@musicapp.com", "user",
					"$2a$10$Nup.FsJ6XeYoSzXeqUaDHu5gacmUKo0pqKeZKFckzNPOsxQfWQBIW", "ADMIN");
			urepository.save(user);

			fsrepository.save(new FavoriteSong(Long.valueOf(5920196), user.getId(), "Lady Gaga", "PokerFace"));
			fsrepository.save(new FavoriteSong(Long.valueOf(805173), user.getId(), "Iron Maiden", "The Trooper"));
			fsrepository.save(
					new FavoriteSong(Long.valueOf(3479257), user.getId(), "Dream Theater", "The Dark Eternal Night"));

			psrepository.save(new PrevSearch("Rihanna", user.getId()));
			psrepository.save(new PrevSearch("Evil Stöö", user.getId()));
			psrepository.save(new PrevSearch("Turmion Kätilöt", user.getId()));
		};
	}
}
