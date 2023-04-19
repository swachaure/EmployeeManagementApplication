package com.employee.management.entity;

import java.math.BigDecimal;
import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.javamoney.moneta.Money;

@Entity
@Table(name = "tax_threshold")
public class TaxThreshold {

	@Id
    @GeneratedValue
	@Column(name = "id", nullable = false)
	Integer id;
	@Column(nullable = false)
	BigDecimal min;
	@Column(nullable = false)
	BigDecimal max;
	@Column(nullable = false)
	BigDecimal additionalAmount;
	@Column(nullable = false)
	BigDecimal taxPercentMultiplier;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getMin() {
		return min;
	}
	public void setMin(BigDecimal min) {
		this.min = min;
	}
	public BigDecimal getMax() {
		return max;
	}
	public void setMax(BigDecimal max) {
		this.max = max;
	}
	public BigDecimal getAdditionalAmount() {
		return additionalAmount;
	}
	public void setAdditionalAmount(BigDecimal additionalAmount) {
		this.additionalAmount = additionalAmount;
	}
	public BigDecimal getTaxPercentMultiplier() {
		return taxPercentMultiplier;
	}
	public void setTaxPercentMultiplier(BigDecimal taxPercentMultiplier) {
		this.taxPercentMultiplier = taxPercentMultiplier;
	}
	
	public Double calculateTax(BigDecimal grossIncome) {
		Double tax = 0D;
		if (this.getMin().compareTo(grossIncome) < 0 && (this.getMax().compareTo(grossIncome) >= 0)) {
			CurrencyUnit usd = Monetary.getCurrency(Locale.US);
			Money money = Money.of(grossIncome.subtract((this.getMin().compareTo(BigDecimal.valueOf(0)) != 0 ? this.getMin().subtract(BigDecimal.valueOf(1)) : BigDecimal.valueOf(0))), usd);
			money = money.multiply(this.getTaxPercentMultiplier()).add(Money.of(this.getAdditionalAmount(), usd)).divideToIntegralValue(12);
			tax = money.getNumber().doubleValue();
			
		}
		return tax;
	}
}
