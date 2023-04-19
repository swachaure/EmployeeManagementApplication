package test.payroll;

public class Employee {
	
	private String firstName;
	private String lastName;
	private int annualSalary;
	private int paymentMonth;
	private double superRate;
	
	
	
	public Employee() {
	}


	public Employee(String firstName, String lastName, int annualSalary, double superRate, int paymentMonth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.annualSalary = annualSalary;
		this.paymentMonth = paymentMonth;
		this.superRate = superRate;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public int getAnnualSalary() {
		return annualSalary;
	}


	public int getPaymentMonth() {
		return paymentMonth;
	}


	public double getSuperRate() {
		return superRate;
	}
	
	
	
	
	
}
