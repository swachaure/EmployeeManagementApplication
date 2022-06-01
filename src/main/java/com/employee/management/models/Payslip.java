package com.employee.management.models;

import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Payslip {

	private int EmployeePaymentMonth;
	private Integer Salary;
	private Double SuperRate;
	private String StartDate;
	private String EndDate;

	public Integer getSalary() {
		return Salary;
	}

	public Double getSuperRate() {
		return SuperRate;
	}

	public String getStartDate() {
		return StartDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setSalary(Integer Salary) {
		this.Salary = Salary;
	}

	public void setSuperRate(Double SuperRate) {
		this.SuperRate = SuperRate;
	}

	public void setStartDate(String StartDate) {
		this.StartDate = StartDate;
	}

	public void setEndDate(String EndDate) {
		this.EndDate = EndDate;
	}

	public int getEmployeePaymentMonth() {
		return EmployeePaymentMonth;
	}

	public void setEmployeePaymentMonth(int employeePaymentMonth) {
		EmployeePaymentMonth = employeePaymentMonth;
	}

	public double CalculateGrossPay() {
		CurrencyUnit usd = Monetary.getCurrency(Locale.US);
		MonetaryAmount money = Money.of(getSalary(), usd);
		money = money.divide(12);
		return money.getNumber().doubleValueExact();
	}

	public Double CalculateNetIncome(double incomeTax) {
		return CalculateGrossPay() - incomeTax;
	}

	public Integer CalculateSuper() {
		return (int) Math.floor(CalculateGrossPay() * (getSuperRate()));
	}

	public void CalculatePayMonth(String month) {

		if (month == null || month.isEmpty()) {
			throw new RuntimeException(" Month passed is invalid!!! ");
		}
		switch (month.toUpperCase()) {
		case "JANUARY":
		case "MARCH":
		case "MAY":
		case "JULY":
		case "AUGUST":
		case "OCTOBER":
		case "DECEMBER":
			setStartDate("01 " + month);
			setEndDate("31 " + month);
			break;

		case "APRIL":
		case "JUNE":
		case "SEPTEMBER":
		case "NOVEMBER":
			setStartDate("01 " + month);
			setEndDate("30 " + month);
			break;
		default:
			setStartDate("01 " + month);
			setEndDate("28 " + month);
			break;
		}
	}
}
