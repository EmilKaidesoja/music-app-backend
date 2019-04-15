package com.project.musicapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.musicapp.web.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

	@Autowired
	private WebController webController;
	
	@Autowired
	private SettingsController settingsController;
	
	@Autowired
	private SpotifyController spotifyController;
	
	@Test
	public void controllerIsPresent() throws Exception {
		assertThat(webController).isNotNull();
		assertThat(settingsController).isNotNull();
		assertThat(spotifyController).isNotNull();
	}	
}
