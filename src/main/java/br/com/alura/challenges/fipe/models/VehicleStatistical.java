package br.com.alura.challenges.fipe.models;

import br.com.alura.challenges.fipe.controllers.enums.TerminalColor;
import br.com.alura.challenges.fipe.utils.CurrencyFormatterUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record VehicleStatistical(
	@JsonProperty("vehicleType")
	String vehicleType,

	@JsonProperty("model")
	String model,

	@JsonProperty("majorPrice")
	@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
	BigDecimal majorPrice,

	@JsonProperty("minorPrice")
	@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
	BigDecimal minorPrice,

	@JsonProperty("averagePrice")
	@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
	BigDecimal averagePrice,

	@JsonProperty("models")
	List<VehicleDataModel> models
) {
	@Override
	public String toString() {
		final var prefix = "R$";
		final var newMajorPrice = CurrencyFormatterUtil.format(majorPrice, prefix);
		final var newMinorPrice = CurrencyFormatterUtil.format(minorPrice, prefix);
		final var newAveragePrice = CurrencyFormatterUtil.format(averagePrice, prefix);
		return "Modelo: %s%s%s, Veículo: %s%s%s, Preços: [maior: %s%s%s, menor: %s%s%s, média: %s%s%s]"
			.formatted(
				TerminalColor.GRAY, model, TerminalColor.RESET,
				TerminalColor.GRAY, vehicleType, TerminalColor.RESET,
				TerminalColor.RED, newMajorPrice, TerminalColor.RESET,
				TerminalColor.GREEN, newMinorPrice, TerminalColor.RESET,
				TerminalColor.BLUE, newAveragePrice, TerminalColor.RESET
			);
	}
}