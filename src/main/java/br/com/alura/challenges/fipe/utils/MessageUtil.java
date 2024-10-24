package br.com.alura.challenges.fipe.utils;

import br.com.alura.challenges.fipe.controllers.enums.TerminalColor;
import br.com.alura.challenges.fipe.controllers.utils.TakeColorComponent;

public class MessageUtil {
	public static void showTitle(final String title, final TakeColorComponent color) {
		System.out.println(
			color.put(TerminalColor.GREEN, '\n' + title.toUpperCase())
		);
	}

	public static void showSubtitle(final String subtitle, final TakeColorComponent color) {
		System.out.println(color.put(TerminalColor.BLUE, subtitle));
	}

	public static void showWarning(final String message, final TakeColorComponent color) {
		System.out.println(color.put(TerminalColor.RED, message));
	}
}
