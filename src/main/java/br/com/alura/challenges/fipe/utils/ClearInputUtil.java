package br.com.alura.challenges.fipe.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class ClearInputUtil {
	public static String removeAccents(final String content) {
		final var normalized = Normalizer.normalize(content, Normalizer.Form.NFD);
		final var pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(normalized).replaceAll("").replaceAll("[^\\p{ASCII}]", "");
	}
}
