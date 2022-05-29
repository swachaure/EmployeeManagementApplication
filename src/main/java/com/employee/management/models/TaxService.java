package com.employee.management.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.javamoney.moneta.Money;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

	public double CalculateIncomeTax(int salary) {
		String taxJsonString = "";
		try {
			String name = new File(".").getCanonicalPath();
			File file = new File(name, "/" + "tax_brackets.json");

			// Creating an object of BufferedReader class
			BufferedReader br = new BufferedReader(new FileReader(file));

			String str;

			while ((str = br.readLine()) != null)
				taxJsonString = taxJsonString + str;
		} catch (Exception e) {
			// TODO: handle exception
		}

		JSONArray taxJsonArray = new JSONArray(taxJsonString);
		CurrencyUnit usd = Monetary.getCurrency(Locale.US);
		for (int i = 0; i < taxJsonArray.length(); i++) {

			JSONObject obj = taxJsonArray.getJSONObject(i);
			Double min = Double.parseDouble((String) obj.get("min"));
			Double max = Double.parseDouble((String) obj.get("max"));
			Double additionalAmount = Double.parseDouble((String) obj.get("additional_amount"));
			Double taxPercentage = Double.parseDouble((String) obj.get("tax_percent_multiplier"));

			if (salary >= min && salary <= max) {
				Money money = Money.of(salary - (min != 0 ? min - 1 : 0), usd);
				money = money.multiply(taxPercentage).add(Money.of(additionalAmount, usd)).divideToIntegralValue(12);
				return money.getNumber().doubleValue();
			}
		}
		return 0D;
	}

}
