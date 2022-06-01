package com.employee.management.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.javamoney.moneta.Money;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class TaxService {
	
	public static List<TaxThreshHold> taxtThreshHolds = List.of(
		new TaxThreshHold(BigDecimal.valueOf(0.0), BigDecimal.valueOf(18200), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0)),
		new TaxThreshHold(BigDecimal.valueOf(18201), BigDecimal.valueOf(37000), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.19)),
		new TaxThreshHold(BigDecimal.valueOf(37001), BigDecimal.valueOf(87000), BigDecimal.valueOf(3572.0), BigDecimal.valueOf(0.325)),
		new TaxThreshHold(BigDecimal.valueOf(87001), BigDecimal.valueOf(180000), BigDecimal.valueOf(19822.0), BigDecimal.valueOf(0.37)),
		new TaxThreshHold(BigDecimal.valueOf(180001), BigDecimal.valueOf(99999999999999999999999999.0), BigDecimal.valueOf(54232.0), BigDecimal.valueOf(0.45))
	);

	public double calculateTax(BigDecimal grossIncome) {
		Optional<Double> tax = taxtThreshHolds.stream().map(t -> t.calculateTax(grossIncome)).filter(t -> t != 0D).findFirst();
		return tax.get();
	}
}
