package com.employee.management.models;

import java.math.BigDecimal;
import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.javamoney.moneta.Money;


public class TaxThreshHold {
	private BigDecimal minIncome;
	private BigDecimal maxIncome;
	private BigDecimal fixedTax;
	private BigDecimal variableTax;
	
	public TaxThreshHold(BigDecimal minIncome, BigDecimal maxIncome, BigDecimal fixedTax, BigDecimal veriableTax) {
		this.minIncome = minIncome;
		this.maxIncome = maxIncome;
		this.fixedTax = fixedTax;
		this.variableTax = veriableTax;
	}
	
	
	public BigDecimal getMinIncome() {
		return minIncome;
	}
	
	public void setMinIncome(BigDecimal minIncome) {
		this.minIncome = minIncome;
	}
	
	public BigDecimal getMaxIncome() {
		return maxIncome;
	}
	
	public void setMaxIncome(BigDecimal maxIncome) {
		this.maxIncome = maxIncome;
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
	
	public Double calculateTax(BigDecimal grossIncome) {
		Double tax = 0D;
		if (this.getMinIncome().compareTo(grossIncome) < 0 && (this.getMaxIncome().compareTo(grossIncome) >= 0)) {
			CurrencyUnit usd = Monetary.getCurrency(Locale.US);
			Money money = Money.of(grossIncome.subtract((this.getMinIncome().compareTo(BigDecimal.valueOf(0)) != 0 ? this.getMinIncome().subtract(BigDecimal.valueOf(1)) : BigDecimal.valueOf(0))), usd);
			money = money.multiply(this.getVariableTax()).add(Money.of(this.getFixedTax(), usd)).divideToIntegralValue(12);
			tax = money.getNumber().doubleValue();
			
		}
		return tax;
	}
	
}

