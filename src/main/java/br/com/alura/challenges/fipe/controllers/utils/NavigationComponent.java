package br.com.alura.challenges.fipe.controllers.utils;

import br.com.alura.challenges.fipe.controllers.enums.TerminalColor;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

@Component
public class NavigationComponent {

	private final Scanner scanner;
	private final TakeColorComponent color;

	public NavigationComponent(
			final Scanner scanner,
			final TakeColorComponent color
	) {
		this.scanner = scanner;
		this.color = color;
	}

	public <T> void showPage(final Integer itemsPerPage, final List<T> elements) {
		final int totalsOfPage = (int) Math.ceil((double) elements.size() / itemsPerPage);
		int currentPage = 0;

		while (true) {
			int start = currentPage * itemsPerPage;
			int end = Math.min((start + itemsPerPage), elements.size());

			for (int i = start; i < end; i++) {
				final var decimalFmt = new DecimalFormat("000");
				final var newCount = decimalFmt.format(i + 1);
				System.out.printf("\t%s %s;\n",
					color.put(TerminalColor.CYAN, (newCount + '-')), elements.get(i)
				);
			}
			System.out.printf("\rPágina atual (%d/%d)\n", (currentPage + 1), totalsOfPage);

			if (end >= elements.size()) { break; }

			System.out.printf("\nPressione [%s] para continuar ou [%s] para sair: ",
				color.put(TerminalColor.BLUE, "Enter"), color.put(TerminalColor.RED, "Q")
			);
			if (isQuit()) { break; }

			currentPage++; // Change to next page
		}
	}

	private boolean isQuit() {
		var choice = scanner.nextLine();
		return choice.equalsIgnoreCase("q");
	}
}
