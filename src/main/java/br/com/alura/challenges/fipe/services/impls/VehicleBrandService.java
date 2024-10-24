package br.com.alura.challenges.fipe.services.impls;

import br.com.alura.challenges.fipe.exceptions.NotFoundException;
import br.com.alura.challenges.fipe.exceptions.RequestException;
import br.com.alura.challenges.fipe.exceptions.TransferProcessingException;
import br.com.alura.challenges.fipe.models.transfers.VehicleBrandDTO;
import br.com.alura.challenges.fipe.services.IQueryService;
import br.com.alura.challenges.fipe.services.IVehicleBrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleBrandService implements IVehicleBrandService {

	private final IQueryService service;

	public VehicleBrandService(final IQueryService service) {
		this.service = service;
	}

	@Override
	public List<VehicleBrandDTO> find(String query, String afterSteps)
			throws NotFoundException, RequestException, TransferProcessingException {
		final var path = query+"/marcas";
		final var result = service.findAll(path, VehicleBrandDTO.class);

		if (result.isEmpty()) {
			throw new NotFoundException(
				"Não foram encontradas marcas para o tipo de veículo especificado!"
			);
		}

		return result;
	}
}
