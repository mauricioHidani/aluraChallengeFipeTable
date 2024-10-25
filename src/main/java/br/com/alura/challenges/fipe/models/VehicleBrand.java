package br.com.alura.challenges.fipe.models;

import br.com.alura.challenges.fipe.controllers.enums.TerminalColor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record VehicleBrand(
		@JsonProperty("code")
		@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
		Integer code,

		@JsonProperty("brand")
		String brand
)  {
	@Override
	public String toString() {
		final var newCode = "%3d".formatted(code());
		return "Código: %s%s%s, Marca: %s%s%s".formatted(
				TerminalColor.GRAY, newCode, TerminalColor.RESET,
				TerminalColor.GRAY, brand(), TerminalColor.RESET
		);
	}
}
