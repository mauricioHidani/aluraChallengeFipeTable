package br.com.alura.challenges.fipe;

import br.com.alura.challenges.fipe.controllers.MenuFipeController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipeApplication implements CommandLineRunner {

	private final MenuFipeController menuFipeController;

	public FipeApplication(final MenuFipeController menuFipeController) {
		this.menuFipeController = menuFipeController;
	}

	public static void main(String[] args) {
		SpringApplication.run(FipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menuFipeController.start();
	}
}