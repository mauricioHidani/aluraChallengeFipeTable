package br.com.alura.challenges.fipe.models.enums;

public enum VehicleType {
	CARS("Carros"),
	MOTORCYCLES("Motos"),
	TRUCKS("Caminhões"),
	UNKNOWN("Desconhecido"),
	;

	private final String label;

	VehicleType(final String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}
}
