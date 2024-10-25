package br.com.alura.challenges.fipe.controllers.utils;

import br.com.alura.challenges.fipe.controllers.enums.TerminalColor;
import org.springframework.stereotype.Component;

@Component
public class TakeColorComponent {
	public String put(final TerminalColor color, final String text) {
		return "%s%s%s".formatted(color, text, TerminalColor.RESET);
	}
}
