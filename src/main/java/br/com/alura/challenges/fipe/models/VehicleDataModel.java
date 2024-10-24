package br.com.alura.challenges.fipe.models;

import br.com.alura.challenges.fipe.models.enums.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VehicleDataModel(
		BigDecimal tablePrice,
		VehicleType type,
		String modelName,
		Integer modelYear,
		LocalDate updateInfoRef,
		String fuelType,
		String fipeCode
) {
}
