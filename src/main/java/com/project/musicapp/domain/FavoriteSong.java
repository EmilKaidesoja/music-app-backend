package com.project.musicapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "favorite_song")
public class FavoriteSong {
	@Id
	private Long id; // this is the musixmatch commontrack_id therefore no auto generation
	private String artistName, songName;
	@Column (name = "userId", nullable = false)
	private Long userId; //saves the usersId to work as a foreign key

	public FavoriteSong() {}

	public FavoriteSong(Long id, Long userId, String artistName, String songName) {
		super();
		this.id = id;
		this.userId = userId;
		this.artistName = artistName;
		this.songName = songName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
			this.userId = userId;	
	}
}
