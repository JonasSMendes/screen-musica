package com.JonasSmendes.musicSoundScreen;

import com.JonasSmendes.musicSoundScreen.principal.Principal;
import com.JonasSmendes.musicSoundScreen.repository.CantorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicSoundScreenApplication implements CommandLineRunner {

	@Autowired
	CantorRepository cantorRepository;


	public static void main(String[] args) {
		SpringApplication.run(MusicSoundScreenApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(cantorRepository);
		principal.exibeMenu();
	}
}
