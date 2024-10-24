package br.com.alura.challenges.fipe.controllers.enums;

public enum MainMenu implements IMenu {
	QUERY("Consultar"),
	QUICK_QUERY("Consulta Rápida"),
	LATEST_BRAND_HISTORY("Último Histórico de Marcas"),
	LATEST_MODEL_HISTORY("Último Histórico de Modelos"),
	LATEST_STATISTICAL_HISTORY("Último Histórico Estatístico"),
	SAVE_HISTORY("Salvar Históricos"),
	EXIT("Sair"),
	;

	private final String label;

	MainMenu(final String label) {
		this.label = label;
	}

	@Override
	public Boolean contains(String target) {
		return null;
	}

	@Override
	public String toString() {
		return label;
	}
}
