package com.project.musicapp.domain;

import javax.persistence.*;

@Entity
public class PrevSearch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String searchWord;
	@Column(name = "userId", nullable = false)
	private Long userId;

	public PrevSearch() {
	}

	public PrevSearch(String searchWord, Long userId) {
		super();
		this.searchWord = searchWord;
		this.userId = userId;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}

}
