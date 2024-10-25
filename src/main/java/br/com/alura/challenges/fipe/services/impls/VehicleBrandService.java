package br.com.alura.challenges.fipe.services.impls;

import br.com.alura.challenges.fipe.exceptions.NotFoundException;
import br.com.alura.challenges.fipe.exceptions.RequestException;
import br.com.alura.challenges.fipe.exceptions.TransferProcessingException;
import br.com.alura.challenges.fipe.models.VehicleBrand;
import br.com.alura.challenges.fipe.models.transfers.ModelDataDTO;
import br.com.alura.challenges.fipe.services.IQueryService;
import br.com.alura.challenges.fipe.services.IVehicleBrandService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleBrandService implements IVehicleBrandService {

	private final IQueryService service;

	public VehicleBrandService(final IQueryService service) {
		this.service = service;
	}

	@Override
	public List<VehicleBrand> find(String query, String afterSteps)
			throws NotFoundException, RequestException, TransferProcessingException {
		final var path = query+"/marcas";
		final var result = service.findAll(path, ModelDataDTO.class);

		if (result.isEmpty()) {
			throw new NotFoundException(
				"Não foram encontradas marcas para o tipo de veículo especificado!"
			);
		}

		return result.stream()
				.map(ModelDataDTO::parseToVehicleBrand)
				.sorted(Comparator.comparing(VehicleBrand::brand))
				.collect(Collectors.toList());
	}
}
