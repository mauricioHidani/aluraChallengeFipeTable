package br.com.alura.challenges.fipe.controllers;

import br.com.alura.challenges.fipe.controllers.enums.MainMenu;
import br.com.alura.challenges.fipe.controllers.utils.ShowMenuComponent;
import br.com.alura.challenges.fipe.models.History;
import org.springframework.stereotype.Controller;

@Controller
public class MenuFipeController {

	private final ShowMenuComponent showMenu;
	private final QueryNormalController queryNormalController;
	private final QuickQueryController quickQueryController;

	private History history;

	public MenuFipeController(
			final ShowMenuComponent showMenu,
			final QueryNormalController queryNormalController,
			final QuickQueryController quickQueryController
	) {
		this.showMenu = showMenu;
		this.queryNormalController = queryNormalController;
		this.quickQueryController = quickQueryController;
	}

	public void start() {
		var option = MainMenu.QUERY;
		do {
			option = showMenu.menuOfCode("Escolha uma operação", MainMenu.class);
			switch (option) {
				case QUERY -> history = queryNormalController.start();
				case QUICK_QUERY -> history = quickQueryController.start();
				case LATEST_BRAND_HISTORY -> System.out.println();
				case LATEST_MODEL_HISTORY -> System.out.println();
				case LATEST_STATISTICAL_HISTORY -> System.out.println();
				case SAVE_HISTORY -> System.out.println();
				default -> System.out.println("Opção inválida");
			}
		} while (option != MainMenu.EXIT);
	}

}
