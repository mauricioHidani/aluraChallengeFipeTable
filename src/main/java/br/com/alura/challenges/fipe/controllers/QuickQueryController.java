package br.com.alura.challenges.fipe.controllers;

import br.com.alura.challenges.fipe.controllers.enums.MainMenu;
import br.com.alura.challenges.fipe.controllers.enums.VehicleTypeMenu;
import br.com.alura.challenges.fipe.controllers.utils.ShowMenuComponent;
import br.com.alura.challenges.fipe.controllers.utils.TakeColorComponent;
import br.com.alura.challenges.fipe.exceptions.NotFoundException;
import br.com.alura.challenges.fipe.exceptions.RequestException;
import br.com.alura.challenges.fipe.exceptions.TransferProcessingException;
import br.com.alura.challenges.fipe.models.History;
import br.com.alura.challenges.fipe.models.VehicleBrand;
import br.com.alura.challenges.fipe.models.VehicleModel;
import br.com.alura.challenges.fipe.models.VehicleStatistical;
import br.com.alura.challenges.fipe.services.IVehicleBrandService;
import br.com.alura.challenges.fipe.services.IVehicleModelService;
import br.com.alura.challenges.fipe.services.IVehicleStatisticalService;
import br.com.alura.challenges.fipe.utils.SuperTitleUtil;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class QuickQueryController {

	private final Scanner scanner;
	private final TakeColorComponent color;
	private final ShowMenuComponent showMenu;
	private final IVehicleBrandService brandService;
	private final IVehicleModelService modelService;
	private final IVehicleStatisticalService statisticalService;


	private VehicleTypeMenu vehicleType;
	private Integer vehicleBrandCode;
	private Integer vehicleModelCode;

	private List<VehicleBrand> brandHistory = new ArrayList<>();
	private List<VehicleModel> modelHistory = new ArrayList<>();
	private VehicleStatistical statisticalHistory;

	public QuickQueryController(
			final Scanner scanner,
			final TakeColorComponent color,
			final ShowMenuComponent showMenu,
			final IVehicleBrandService brandService,
			final IVehicleModelService modelService,
			final IVehicleStatisticalService statisticalService
	) {
		this.scanner = scanner;
		this.color = color;
		this.showMenu = showMenu;
		this.brandService = brandService;
		this.modelService = modelService;
		this.statisticalService = statisticalService;
	}

	/**
	 * <h1>start</h1>
	 * <h3>Inicia Consulta Rápida</h3>
	 * Realiza a inicialização da consulta rápida.
	 * @return History Histórico da consulta.
	 * @since Thursday, 24th October 2024.
	 * */
	public History start() {
		SuperTitleUtil.show(MainMenu.QUICK_QUERY.toString(), color);
		findBrand();
		findModel();
		findStatistical();

		System.out.println(statisticalHistory);

		return new History(statisticalHistory, brandHistory, modelHistory);
	}

	/**
	 * <h1>resetHistories</h1>
	 * <h3>Reseta Históricos</h3>
	 * Realiza RESET das informações do hitórico atual.
	 * */
	protected void resetHistories() {
		if (!brandHistory.isEmpty()) {
			brandHistory = new ArrayList<>();
		}
		if (!modelHistory.isEmpty()) {
			modelHistory = new ArrayList<>();
		}
		if (statisticalHistory != null) {
			statisticalHistory = null;
		}
	}

	/**
	 * <h1>findBrand</h1>
	 * <h3>Encontra Marcas</h3>
	 * Procura as marcas do tipo de veículo especificado por meio do seu nome, sendo que este é consultado na API sem
	 * a utilização de acentuação, onde o mesmo já é tratado antes de realizar a consulta.
	 *
	 * @return List&lt;VehicleBrand&gt; Lista das marcas encontradas.
	 * @throws NotFoundException Quando não encontrado nenhuma marca do veículo.
	 * @throws RequestException Caso houver qualquer erro ao requisitar a API aos valores da Tabela FIPE.
	 * @throws IllegalArgumentException Quando informado um veículo inválido.
	 * @throws TransferProcessingException Erro ao tentar converter resposta da requisição da API para objeto.
	 * @since Thursday, 24th October 2024.
	 * */
	protected void findBrand()
			throws NotFoundException, RequestException, TransferProcessingException, IllegalArgumentException {
		vehicleType = showMenu.simpleMenuOfLabel("Tipo de veículo: ", VehicleTypeMenu.class);
		if (vehicleType == null) {
			throw new IllegalArgumentException("Tipo de veículo é inválido!");
		}
		brandHistory = brandService.find(vehicleType.getLabel(), "");
	}

	/**
	 * <h1>findModel</h1>
	 * <h3>Encontra Modelos</h3>
	 * Procura pelos modelos do veículo especificado a partir do código da marca informada.
	 *
	 * @throws NotFoundException Quando não encontrado modelos com o código da marca especificada.
	 * @throws RequestException Caso não for possivel realizar a consulta na API da Tabela FIPE.
	 * @throws TransferProcessingException Erro ao tentar converter resposta da requisição da API para objeto.
	 * @throws IllegalArgumentException Quando o código da marca informada não é válido.
	 * @since Thursday, 24th October 2024.
	 * */
	protected void findModel()
			throws NotFoundException, RequestException, TransferProcessingException, IllegalArgumentException {
		System.out.print("Código da marca: ");
		vehicleBrandCode = scanner.nextInt();
		scanner.nextLine();

		modelHistory = modelService.find(vehicleBrandCode.toString(), vehicleType.getLabel());
	}

	/**
	 * <h1>findStatistical</h1>
	 * <h3>Encontra a Estatística</h3>
	 * Realiza a consulta a partir do códico da marca e do modelo especificado para montar informações estatísticas a
	 * partir da Tabela FIPE.
	 *
	 * @throws NotFoundException Quando não encontrado modelos para montar a estatística.
	 * @throws RequestException Caso não for possivel realizar a consulta na API da Tabela FIPE.
	 * @throws TransferProcessingException Erro ao tentar converter resposta da requisição da API para objeto.
	 * @throws IllegalArgumentException Quando o código do modelo informado não é válido.
	 * @since Thursday, 24th October 2024.
	 * */
	protected void findStatistical()
			throws NotFoundException, RequestException, TransferProcessingException, IllegalArgumentException {
		System.out.print("Código do modelo: ");
		vehicleModelCode = scanner.nextInt();
		scanner.nextLine();

		statisticalHistory = statisticalService.find(
			vehicleType.getLabel(), vehicleBrandCode.toString(), vehicleModelCode.toString()
		);
	}
}
