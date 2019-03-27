package com.project.musicapp.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FavoriteSongRepository extends CrudRepository<FavoriteSong, Long>{
	List<FavoriteSong> findByUserId(Long userId);
}
