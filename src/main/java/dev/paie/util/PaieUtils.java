package dev.paie.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class PaieUtils {
	/**
	 * Formate un nombre sous la forme xx.xx (exemple : 2.00, 1.90). L'arrondi se
	 * faire en mode "UP" => 1.904 devient 1.91
	 *
	 * @param decimal
	 *            nombre à formater
	 * @return le nombre formaté
	 */
	public String formaterBigDecimal(BigDecimal decimal) {
		DecimalFormat decimalFormat = new DecimalFormat();
		// forcer le séparateur "." même sur un poste en français
		decimalFormat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.UK));
		decimalFormat.setMaximumFractionDigits(2);
		decimalFormat.setRoundingMode(RoundingMode.UP);
		decimalFormat.setMinimumFractionDigits(2);
		decimalFormat.setGroupingUsed(false);
		return decimalFormat.format(decimal);
	}
}