package br.com.alura.challenges.fipe.models.transfers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleManyModelDTO(
		@JsonAlias("modelos") List<ModelDataDTO> models
) {
}
