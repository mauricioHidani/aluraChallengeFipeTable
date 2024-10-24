package br.com.alura.challenges.fipe.controllers.utils;

import br.com.alura.challenges.fipe.controllers.enums.IMenu;
import br.com.alura.challenges.fipe.controllers.enums.TerminalColor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ShowMenuComponent {

	private final Scanner scanner;
	private final TakeColorComponent color;

	public ShowMenuComponent(
			final Scanner scanner,
			final TakeColorComponent color
	) {
		this.scanner = scanner;
		this.color = color;
	}

	public <STATE extends Enum<STATE> & IMenu> STATE menuOfCode(
			final String message,
			final Class<STATE> options
	) {
		final var states = getOptions(options);

		System.out.println(message);

		showMenu(states, true);
		System.out.print("Escolha: ");
		final var choice = scanner.nextInt();
		scanner.nextLine();

		if ((choice - 1) < states.length) {
			return states[choice - 1];
		}

		System.out.println("Opção inválida!");
		return null;
	}

	public <STATE extends Enum<STATE> & IMenu> STATE simpleMenuOfLabel(
			final String message,
			final Class<STATE> options
	) {
		System.out.print(message);
		final var states = getOptions(options);
		final var choice = scanner.nextLine();

		return getChoiceOfLabel(choice, states);
	}

	public <STATE extends Enum<STATE> & IMenu> STATE menuOfLabel(
			final String message,
			final Class<STATE> options
	) {
		final var states = getOptions(options);

		System.out.println(message);

		showMenu(states, false);
		System.out.print("tipo: ");
		final var choice = scanner.nextLine();

		return getChoiceOfLabel(choice, states);
	}

	private <STATE extends Enum<STATE> & IMenu> STATE[] getOptions(final Class<STATE> options) {
		return options.getEnumConstants();
	}

	private <OPTION extends Enum<OPTION> & IMenu> void showMenu(
			final OPTION[] OPTIONS,
			final Boolean showIndex
	) {
		for (int i = 0; i < OPTIONS.length; i++) {
			var parameters = new Object[]{
				color.put(TerminalColor.CYAN, "-"), OPTIONS[i]
			};
			if (showIndex) {
				parameters = new Object[]{
					color.put(TerminalColor.CYAN, ((i + 1) + "-")), OPTIONS[i]
				};
			}
			System.out.printf("\t%s %s\n", parameters);
		}
	}

	private <STATE extends Enum<STATE> & IMenu> STATE getChoiceOfLabel(
			final String choice,
			final STATE[] states
	) {
		Optional<STATE> modelType = Arrays.stream(states)
				.filter(state -> state.contains(choice))
				.findFirst();
		if (modelType.isEmpty()) {
			System.out.println("Opção inválida.");
			return null;
		}
		return modelType.get();
	}
}
