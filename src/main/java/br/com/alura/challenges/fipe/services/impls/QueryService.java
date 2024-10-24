package br.com.alura.challenges.fipe.services.impls;

import br.com.alura.challenges.fipe.configs.FipeApiConfig;
import br.com.alura.challenges.fipe.exceptions.RequestException;
import br.com.alura.challenges.fipe.exceptions.TransferProcessingException;
import br.com.alura.challenges.fipe.services.IQueryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class QueryService implements IQueryService {

	private final FipeApiConfig config;
	private final ObjectMapper mapper;

	public QueryService(
			final FipeApiConfig config,
			final ObjectMapper mapper
	) {
		this.config = config;
		this.mapper = mapper;
	}

	@Override
	public <T> T find(final String query, final Class<T> clazz)
			throws RequestException, TransferProcessingException {
		try {
			final var response = request(query).body();
			return mapper.readValue(response, clazz);

		} catch (JsonProcessingException e) {
			throw new TransferProcessingException("A resposta não pode ser processada.");
		}
	}

	@Override
	public <T> List<T> findAll(final String query, final Class<T> clazz)
			throws RequestException, TransferProcessingException {
		try {
			var list = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
			final var response = request(query).body();
			return mapper.readValue(response, list);

		} catch (JsonProcessingException e) {
			throw new TransferProcessingException("A resposta não pode ser processada.");
		}
	}

	private HttpResponse<String> request(final String path) {
		try {
			final var crrPath = config.getPath() + path;
			final var client = HttpClient.newHttpClient();
			final var request = HttpRequest.newBuilder().uri(URI.create(crrPath)).build();
			return client.send(request, HttpResponse.BodyHandlers.ofString());

		} catch (UncheckedIOException | IOException e) {
			throw new RequestException("Falha ao realizar a requisição.");
		} catch (InterruptedException e) {
			throw new RequestException("A operação foi interrompida antes que pudesse prosseguir.");
		}
	}
}
