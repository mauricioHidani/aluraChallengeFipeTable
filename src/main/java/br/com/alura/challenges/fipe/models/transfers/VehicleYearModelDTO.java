package br.com.alura.challenges.fipe.models.transfers;

import br.com.alura.challenges.fipe.models.VehicleDataModel;
import br.com.alura.challenges.fipe.models.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleYearModelDTO(
		@JsonAlias("Valor") String tablePrice,
		@JsonAlias("TipoVeiculo") Integer typeCode,
		@JsonAlias("Modelo") String modelName,
		@JsonAlias("AnoModelo") Integer modelYear,
		@JsonAlias("MesReferencia") String updateInfoRef,
		@JsonAlias("Combustivel") String fuelType,
		@JsonAlias("CodigoFipe") String fipeCode
) {
	public VehicleDataModel parseToDataModel() {
		return new VehicleDataModel(
				parseTablePrice(),
				parseType(),
				modelName,
				modelYear,
				parseUpdateInfoRef(),
				fuelType,
				fipeCode
		);
	}

	private BigDecimal parseTablePrice() {
		try {
			return new BigDecimal(
				tablePrice.replaceAll("[^0-9,]", "").replaceAll(",", ".")
			);
		} catch (NumberFormatException e) {
			return BigDecimal.ZERO;
		}
	}

	private VehicleType parseType() {
		final var types = VehicleType.values();
		if (typeCode < 0 || typeCode > types.length) {
			return types[types.length -1];
		}
		return types[typeCode -1];
	}

	private LocalDate parseUpdateInfoRef() {
		try {
			final var locale = new Locale("pt", "BR");
			final var fmt = DateTimeFormatter.ofPattern("MMMM 'de' yyyy", locale);
			final var updated = fmt.parse(updateInfoRef);
			return LocalDate.of(
				updated.get(ChronoField.YEAR),
				updated.get(ChronoField.MONTH_OF_YEAR),
				1
			);
		} catch (DateTimeException e) {
			return LocalDate.now();
		}
	}
}
