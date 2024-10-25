package br.com.alura.challenges.fipe.services.impls;

import br.com.alura.challenges.fipe.exceptions.SaveException;
import br.com.alura.challenges.fipe.services.IVehicleHistorySaveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class VehicleHistorySaveService implements IVehicleHistorySaveService {

	private final ObjectMapper mapper;

	public VehicleHistorySaveService(final ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public <HISTORY> void save(HISTORY history) {
		try {
			FileWriter writer = new FileWriter("history.json");
			final String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(history);
			writer.write(json);
			writer.close();

		} catch (IOException e) {
			throw new SaveException("Não foi possivel realizar o salvamento do histórico de endereços!");
		}
	}
}
