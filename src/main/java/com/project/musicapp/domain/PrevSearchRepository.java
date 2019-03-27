package com.project.musicapp.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PrevSearchRepository extends CrudRepository<PrevSearch, Long>{
	List<PrevSearch> findByUserId(Long userId);
}
