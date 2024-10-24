package br.com.alura.challenges.fipe.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CurrencyFormatterUtil {
	public static String format(final BigDecimal value, final String prefix) {
		var currencyFmt = new DecimalFormat("#,##0.00");
		currencyFmt.setPositivePrefix(prefix + " ");

		return currencyFmt.format(value);
	}
}
