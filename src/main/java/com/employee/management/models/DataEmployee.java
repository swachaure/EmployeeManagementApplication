package com.employee.management.models;

import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class DataEmployee {
	private String EmployeeFirstName;
	private String EmployeeSurname;

	public DataEmployee() {
		this.EmployeeFirstName = null;
		this.EmployeeSurname = null;

	}

	public String getEmployeeFirstName() {
		return EmployeeFirstName;
	}

	public String getEmployeeSurname() {
		return EmployeeSurname;
	}

	public void setEmployeeFirstName(String FirstName) {
		this.EmployeeFirstName = FirstName;
	}

	public void setEmployeeSurname(String Surname) {
		this.EmployeeSurname = Surname;
	}

}