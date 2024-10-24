package br.com.alura.challenges.fipe.controllers;

import br.com.alura.challenges.fipe.controllers.enums.MainMenu;
import br.com.alura.challenges.fipe.controllers.utils.ShowMenuComponent;
import org.springframework.stereotype.Controller;

@Controller
public class MenuFipeController {

	private final ShowMenuComponent showMenu;

	public MenuFipeController(final ShowMenuComponent showMenu) {
		this.showMenu = showMenu;
	}

	public void start() {
		var option = MainMenu.QUERY;
		do {
			option = showMenu.menuOfCode("Escolha uma operação", MainMenu.class);
			switch (option) {
				case QUERY -> System.out.println();
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
