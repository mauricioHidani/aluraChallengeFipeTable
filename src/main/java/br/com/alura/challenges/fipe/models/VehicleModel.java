package br.com.alura.challenges.fipe.models;

import br.com.alura.challenges.fipe.controllers.enums.TerminalColor;

public record VehicleModel(
		Integer code,
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
