package br.com.alura.challenges.fipe.utils;

import br.com.alura.challenges.fipe.controllers.enums.TerminalColor;
import br.com.alura.challenges.fipe.controllers.utils.TakeColorComponent;

public class SuperTitleUtil {
	public static void show(final String title, final TakeColorComponent color) {
		System.out.println(
			color.put(TerminalColor.GREEN, '\n' + title.toUpperCase())
		);
	}
}
