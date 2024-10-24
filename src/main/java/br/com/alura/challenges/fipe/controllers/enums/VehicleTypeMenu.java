package br.com.alura.challenges.fipe.controllers.enums;

import br.com.alura.challenges.fipe.utils.ClearInputUtil;

public enum VehicleTypeMenu implements IMenu {
	CARS("Carros"),
	MOTORCYCLES("Motos"),
	TRUCKS("Caminhões"),
	;

	private final String label;

	VehicleTypeMenu(final String label) {
		this.label = label;
	}

	public String getLabel() {
		return ClearInputUtil.removeAccents(label).toLowerCase();
	}

	@Override
	public Boolean contains(String target) {
		final var newTarget = ClearInputUtil.removeAccents(target).toLowerCase();
		final var crrTarget = ClearInputUtil.removeAccents(this.toString()).toLowerCase();

		return crrTarget.contains(newTarget);
	}

	@Override
	public String toString() {
		return label;
	}
}
