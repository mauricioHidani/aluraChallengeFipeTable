package br.com.alura.challenges.fipe.models.transfers;

import br.com.alura.challenges.fipe.models.VehicleBrand;
import br.com.alura.challenges.fipe.models.VehicleModel;
import com.fasterxml.jackson.annotation.JsonAlias;

public record ModelDataDTO(
	@JsonAlias("codigo") String code,
	@JsonAlias("nome") String name
) {
	public VehicleBrand parseToVehicleBrand() {
		return new VehicleBrand(Integer.parseInt(code), name);
	}

	public VehicleModel parseToVehicleModel() {
		return new VehicleModel(Integer.parseInt(code), name);
	}
}
