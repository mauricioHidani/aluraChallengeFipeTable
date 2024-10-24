package br.com.alura.challenges.fipe.models.transfers;

import br.com.alura.challenges.fipe.controllers.enums.TerminalColor;
import com.fasterxml.jackson.annotation.JsonAlias;

public record VehicleModelDTO(
		@JsonAlias({"codigo", "code"}) String code,
		@JsonAlias({"nome", "model"}) String model
) {
	@Override
	public String toString() {
		final var newCode = "%3d".formatted(Integer.parseInt(code));
		return "Código: %s%s%s, Marca: %s%s%s".formatted(
			TerminalColor.GRAY, newCode, TerminalColor.RESET,
			TerminalColor.GRAY, model, TerminalColor.RESET
		);
	}
}
