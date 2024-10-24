package br.com.alura.challenges.fipe.controllers;

import br.com.alura.challenges.fipe.controllers.enums.MainMenu;
import br.com.alura.challenges.fipe.controllers.utils.NavigationComponent;
import br.com.alura.challenges.fipe.controllers.utils.TakeColorComponent;
import br.com.alura.challenges.fipe.utils.MessageUtil;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LatestHistoryController {

	private final Integer ITEMS_PER_PAGE = 10;

	private final TakeColorComponent color;
	private final NavigationComponent navigation;

	public LatestHistoryController(
			final TakeColorComponent color,
			final NavigationComponent navigation
	) {
		this.color = color;
		this.navigation = navigation;
	}

	/**
	 * <h1>start</h1>
	 * <h3>Inicia Histórico</h3>
	 * Incia o último histórico consultado e o mostra de forma paginada.
	 * @since Thursday, 24th October 2024.
	 * */
	public <HISTORY> void start(final List<HISTORY> history, final MainMenu menu) {
		MessageUtil.showTitle(menu.toString(), color);
		if (history != null && !history.isEmpty()) {
			navigation.showPage(ITEMS_PER_PAGE, history);
			return;
		}
		MessageUtil.showWarning("Não há histórico para mostrar!", color);
	}

	/**
	 * <h1>start</h1>
	 * <h3>Inicia Histórico</h3>
	 * Incia o último histórico consultado e o mostra de forma simples.
	 * @since Thursday, 24th October 2024.
	 * */
	public <HISTORY> void start(final HISTORY history, final MainMenu menu) {
		MessageUtil.showTitle(menu.toString(), color);
		if (history != null) {
			System.out.println(history);
			return;
		}
		MessageUtil.showWarning("Não há histórico para mostrar!", color);
	}

}
