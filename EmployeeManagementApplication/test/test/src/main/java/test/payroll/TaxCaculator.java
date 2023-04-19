package test.payroll;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;

public class TaxCaculator {
	
	public static List<TaxThreshold> taxThresholds = Lists.newArrayList(
		new TaxThreshold(BigDecimal.valueOf(0.0), BigDecimal.valueOf(18200), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0)),
		new TaxThreshold(BigDecimal.valueOf(18200), BigDecimal.valueOf(37000), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.19)),
		new TaxThreshold(BigDecimal.valueOf(37000), BigDecimal.valueOf(87000), BigDecimal.valueOf(3572.0), BigDecimal.valueOf(0.325)),
		new TaxThreshold(BigDecimal.valueOf(87000), BigDecimal.valueOf(180000), BigDecimal.valueOf(19822.0), BigDecimal.valueOf(0.37)),
		new TaxThreshold(BigDecimal.valueOf(180000), null, BigDecimal.valueOf(54232.0), BigDecimal.valueOf(0.45))				
	);
	
	public static Optional<BigDecimal> calculateTax(BigDecimal grossIncome) {
		Optional<BigDecimal> tax = taxThresholds.stream().map(t -> t.calculateTax(grossIncome)).filter(t -> t != null).findFirst();
		return tax;
	}

}
