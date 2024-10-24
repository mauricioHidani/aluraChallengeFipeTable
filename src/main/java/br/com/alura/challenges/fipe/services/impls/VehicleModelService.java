package br.com.alura.challenges.fipe.services.impls;

import br.com.alura.challenges.fipe.exceptions.NotFoundException;
import br.com.alura.challenges.fipe.exceptions.RequestException;
import br.com.alura.challenges.fipe.exceptions.TransferProcessingException;
import br.com.alura.challenges.fipe.models.VehicleModel;
import br.com.alura.challenges.fipe.models.transfers.ModelDataDTO;
import br.com.alura.challenges.fipe.models.transfers.VehicleManyModelDTO;
import br.com.alura.challenges.fipe.services.IQueryService;
import br.com.alura.challenges.fipe.services.IVehicleModelService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleModelService implements IVehicleModelService {

	private final IQueryService service;

	public VehicleModelService(final IQueryService service) {
		this.service = service;
	}

	@Override
	public List<VehicleModel> find(String query, String afterSteps)
			throws NotFoundException, RequestException, TransferProcessingException {
		final var path = afterSteps + "/marcas/" + query + "/modelos";
		final var result = service.find(path, VehicleManyModelDTO.class);

		if (result.isEmpty()) {
			throw new NotFoundException(
				"Não foram encontrados os modelos da marca especificada!"
			);
		}
		return result.get().models().stream()
				.map(ModelDataDTO::parseToVehicleModel)
				.sorted(Comparator.comparing(VehicleModel::model))
				.collect(Collectors.toList());
	}
}
