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
	private final LatestHistoryController latestHistoryController;
	private final SaveHistoryController saveHistoryController;

	private History history;

	public MenuFipeController(
			final ShowMenuComponent showMenu,
			final QueryNormalController queryNormalController,
			final QuickQueryController quickQueryController,
			final LatestHistoryController latestHistoryController,
			final SaveHistoryController saveHistoryController
	) {
		this.showMenu = showMenu;
		this.queryNormalController = queryNormalController;
		this.quickQueryController = quickQueryController;
		this.latestHistoryController = latestHistoryController;
		this.saveHistoryController = saveHistoryController;
	}

	public void start() {
		var option = MainMenu.QUERY;
		do {
			option = showMenu.menuOfCode("\nEscolha uma operação", MainMenu.class);
			switch (option) {
				case QUERY -> history = queryNormalController.start();
				case QUICK_QUERY -> history = quickQueryController.start();
				case LATEST_BRAND_HISTORY -> latestHistoryController.start(history.brandHistory(), option);
				case LATEST_MODEL_HISTORY -> latestHistoryController.start(history.modelHistory(), option);
				case LATEST_STATISTICAL_HISTORY -> latestHistoryController.start(history.statisticalHistory(), option);
				case SAVE_HISTORY -> saveHistoryController.start(history);
				case EXIT -> System.out.println("Aplicação de consulta da Tabela Fipe encerrada...");
				default -> System.out.println("Opção inválida");
			}
		} while (option != MainMenu.EXIT);
	}

}
