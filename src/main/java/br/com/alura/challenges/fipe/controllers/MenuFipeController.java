package br.com.alura.challenges.fipe.controllers;

import br.com.alura.challenges.fipe.controllers.enums.MainMenu;
import br.com.alura.challenges.fipe.controllers.utils.ShowMenuComponent;
import br.com.alura.challenges.fipe.models.History;
import br.com.alura.challenges.fipe.models.VehicleBrand;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuFipeController {

	private final ShowMenuComponent showMenu;
	private final QueryNormalController queryNormalController;

	private History history;

	public MenuFipeController(
			final ShowMenuComponent showMenu,
			final QueryNormalController queryNormalController
	) {
		this.showMenu = showMenu;
		this.queryNormalController = queryNormalController;
	}

	public void start() {
		var option = MainMenu.QUERY;
		do {
			option = showMenu.menuOfCode("Escolha uma operação", MainMenu.class);
			switch (option) {
				case QUERY -> history = queryNormalController.start();
				case QUICK_QUERY -> System.out.println();
				case LATEST_BRAND_HISTORY -> System.out.println();
				case LATEST_MODEL_HISTORY -> System.out.println();
				case LATEST_STATISTICAL_HISTORY -> System.out.println();
				case SAVE_HISTORY -> System.out.println();
				default -> System.out.println("Opção inválida");
			}
		} while (option != MainMenu.EXIT);
	}

}
