package br.com.alura.challenges.fipe.models;

import br.com.alura.challenges.fipe.controllers.enums.TerminalColor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record VehicleModel(
		@JsonProperty("code")
		@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
		Integer code,

		@JsonProperty("model")
		String model
) {
	@Override
	public String toString() {
		final var newCode = "%5d".formatted(code());
		return "Código: %s%s%s, Modelo: %s%s%s".formatted(
				TerminalColor.GRAY, newCode, TerminalColor.RESET,
				TerminalColor.GRAY, model(), TerminalColor.RESET
		);
	}
}
