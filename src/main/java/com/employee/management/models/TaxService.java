package com.employee.management.models;

import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;
@Service
public class TaxService {

	public double CalculateIncomeTax(int salary) {
		CurrencyUnit usd = Monetary.getCurrency(Locale.US);
		if (salary <= 37000 && salary >= 18201) {
			Money money = Money.of(salary - 18200, usd);
			money = money.multiply(0.19).divideToIntegralValue(12);
			return money.getNumber().doubleValue();
		} else if (salary <= 87000 && salary >= 37001) {
			Money money = Money.of(salary - 37000, usd);
			money = money.multiply(0.325).add(Money.of(3572, usd)).divideToIntegralValue(12);
			return money.getNumber().doubleValue();
		} else if (salary <= 180000 && salary >= 87001) {
			Money money = Money.of(salary - 87000, usd);
			money = money.multiply(0.37).add(Money.of(19822, usd)).divideToIntegralValue(12);
			return money.getNumber().doubleValue();
		} else if (salary >= 180001) {
			Money money = Money.of(salary - 180000, usd);
			money = money.multiply(0.45).add(Money.of(54232, usd)).divideToIntegralValue(12);
			return money.getNumber().doubleValue();
		}
		return 0D;
	}

}
