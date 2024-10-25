package br.com.alura.challenges.fipe.services.impls;

import br.com.alura.challenges.fipe.exceptions.NotFoundException;
import br.com.alura.challenges.fipe.exceptions.RequestException;
import br.com.alura.challenges.fipe.exceptions.TransferProcessingException;
import br.com.alura.challenges.fipe.models.VehicleDataModel;
import br.com.alura.challenges.fipe.models.VehicleStatistical;
import br.com.alura.challenges.fipe.models.transfers.ModelDataDTO;
import br.com.alura.challenges.fipe.models.transfers.VehicleYearModelDTO;
import br.com.alura.challenges.fipe.services.IQueryService;
import br.com.alura.challenges.fipe.services.IVehicleStatisticalService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleStatisticalService implements IVehicleStatisticalService {

	private final IQueryService service;

	public VehicleStatisticalService(final IQueryService service) {
		this.service = service;
	}

	@Override
	public VehicleStatistical find(String type, String brandCode, String modelCode)
			throws NotFoundException, RequestException, TransferProcessingException {
		final var path = "%s/marcas/%s/modelos/%s/anos".formatted(type, brandCode, modelCode);
		final var models = findModels(path);
		final var stats = getStatistics(models);

		final var firstModel = models.get(0);
		final var result = new VehicleStatistical(
				firstModel.type().toString(),
				firstModel.modelName(),
				BigDecimal.valueOf(stats.getMax()),
				BigDecimal.valueOf(stats.getMin()),
				BigDecimal.valueOf(stats.getAverage()),
				models
		);

		return Optional.of(result)
				.orElseThrow(() -> new NotFoundException(
					"Não foram encontradas informações estatísticas do veículo!"
				));
	}

	protected List<VehicleDataModel> findModels(final String path) {
		final var modelsOfYears = service.findAll(path, ModelDataDTO.class);
		if (modelsOfYears.isEmpty()) {
			throw new NotFoundException(
				"Não foi encontrado modelos com os anos correspondentes ao modelo na table FIPE!"
			);
		}

		List<VehicleDataModel> founded = new ArrayList<>();
		modelsOfYears.forEach(model -> {
			final var res = service.find((path + "/" + model.code()), VehicleYearModelDTO.class)
					.orElseThrow(() -> new NotFoundException(""));
			founded.add(res.parseToDataModel());
		});
		return founded;
	}

	protected DoubleSummaryStatistics getStatistics(final List<VehicleDataModel> models) {
		return models.stream()
				.filter(model -> model.tablePrice().compareTo(BigDecimal.ZERO) > 0)
				.collect(Collectors.summarizingDouble(model -> model.tablePrice().doubleValue()));
	}
}
