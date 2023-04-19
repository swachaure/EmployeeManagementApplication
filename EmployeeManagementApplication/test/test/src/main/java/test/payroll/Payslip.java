package test.payroll;

import java.math.BigDecimal;
import java.util.Date;

public class Payslip {
	
	private Employee employee;
	private Date fromDate;
	private Date toDate;
	private BigDecimal grossIncome;
	private BigDecimal incomeTax;
	private BigDecimal superannuation;
	private BigDecimal netIncome;
	
		
	public Payslip() {
	}


	public Payslip(Employee employee, Date fromDate, Date toDate, BigDecimal grossIncome, BigDecimal incomeTax,
			BigDecimal superannuation, BigDecimal netIncome) {

		this.employee = employee;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.grossIncome = grossIncome;
		this.incomeTax = incomeTax;
		this.superannuation = superannuation;
		this.netIncome = netIncome;
	}


	public Employee getEmployee() {
		return employee;
	}


	public Date getFromDate() {
		return fromDate;
	}


	public Date getToDate() {
		return toDate;
	}


	public BigDecimal getGrossIncome() {
		return grossIncome;
	}


	public BigDecimal getIncomeTax() {
		return incomeTax;
	}


	public BigDecimal getSuperannuation() {
		return superannuation;
	}


	public BigDecimal getNetIncome() {
		return netIncome;
	}


	@Override
	public String toString() {
		return "Payslip [employee=" + employee + ", fromDate=" + fromDate + ", toDate=" + toDate + ", grossIncome="
				+ grossIncome + ", incomeTax=" + incomeTax + ", superannuation=" + superannuation + ", netIncome="
				+ netIncome + "]";
	}
	
	
	
	

}
