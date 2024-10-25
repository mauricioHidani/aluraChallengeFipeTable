package br.com.alura.challenges.fipe.controllers;

import br.com.alura.challenges.fipe.controllers.utils.TakeColorComponent;
import br.com.alura.challenges.fipe.exceptions.SaveException;
import br.com.alura.challenges.fipe.models.History;
import br.com.alura.challenges.fipe.services.IVehicleHistorySaveService;
import br.com.alura.challenges.fipe.utils.MessageUtil;
import org.springframework.stereotype.Controller;

@Controller
public class SaveHistoryController {

	private final TakeColorComponent color;
	private final IVehicleHistorySaveService service;

	public SaveHistoryController(
			final TakeColorComponent color,
			final IVehicleHistorySaveService service
	) {
		this.color = color;
		this.service = service;
	}

	public void start(final History history) {
		MessageUtil.showTitle("Salvar histórico", color);
		try	{
			service.save(history);
			System.out.println("Histórico salvo!");

		} catch (SaveException e) {
			MessageUtil.showWarning(e.getMessage(), color);
		}
	}
}
