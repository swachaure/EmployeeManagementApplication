package test.payroll;

import java.math.BigDecimal;

public class TaxThreshold {

	private BigDecimal minimumIncome;
	private BigDecimal maximumIncome;
	private BigDecimal fixedTax;
	private BigDecimal variableTax;

	public TaxThreshold(BigDecimal minimumIncome, BigDecimal maximumIncome, BigDecimal fixedTax,
			BigDecimal variableTax) {
		this.minimumIncome = minimumIncome;
		this.maximumIncome = maximumIncome;
		this.fixedTax = fixedTax;
		this.variableTax = variableTax;
	}

	public BigDecimal getMinimumIncome() {
		return minimumIncome;
	}

	public void setMinimumIncome(BigDecimal minimumIncome) {
		this.minimumIncome = minimumIncome;
	}

	public BigDecimal getMaximumIncome() {
		return maximumIncome;
	}

	public void setMaximumIncome(BigDecimal maximumIncome) {
		this.maximumIncome = maximumIncome;
	}

	public BigDecimal getFixedTax() {
		return fixedTax;
	}

	public void setFixedTax(BigDecimal fixedTax) {
		this.fixedTax = fixedTax;
	}

	public BigDecimal getVariableTax() {
		return variableTax;
	}

	public void setVariableTax(BigDecimal variableTax) {
		this.variableTax = variableTax;
	}

	public BigDecimal calculateTax(BigDecimal grossIncome) {
		BigDecimal tax = null;
		if (minimumIncome.compareTo(grossIncome) < 0
				&& (maximumIncome == null || maximumIncome.compareTo(grossIncome) >= 0)) {
			tax = ((grossIncome.subtract(minimumIncome)).multiply(variableTax))
					.add(fixedTax);
		}
		return tax;
	}

}
