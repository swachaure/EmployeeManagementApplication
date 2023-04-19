package test.payroll;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Payroll {

	private ObjectMapper objectMapper = new ObjectMapper();
	private SimpleDateFormat df = new SimpleDateFormat("dd MMMMM");

	public Payroll() {
		objectMapper.setDateFormat(df);
	}

	public Payslip run(Employee employee) {
		BigDecimal grossIncome = BigDecimal.valueOf(employee.getAnnualSalary() / 12);
//		grossIncomeAfterSupper = grossIncome.divide(BigDecimal.valueOf(1 + employee.getSalary().getSuperRate()), 2, RoundingMode.HALF_UP);
//		superannuation = grossIncome.subtract(grossIncomeAfterSupper);

		BigDecimal superannuation = grossIncome.multiply(BigDecimal.valueOf(employee.getSuperRate())).setScale(0,
				RoundingMode.HALF_UP);

		Optional<BigDecimal> incomeTaxOptional = TaxCaculator
				.calculateTax(BigDecimal.valueOf(employee.getAnnualSalary()));
		BigDecimal incomeTax = incomeTaxOptional.get().divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP);
		BigDecimal netIncome = grossIncome.subtract(incomeTax);

		Payslip payslip = new Payslip(employee, getPaymentStartDate(employee.getPaymentMonth()),
				getPaymentEndDate(employee.getPaymentMonth()), grossIncome, incomeTax, superannuation, netIncome);

		return payslip;
	}

	public List<Payslip> run(List<Employee> employees) {
		List<Payslip> payslips = employees.stream().map(e -> run(e)).collect(Collectors.toList());
		return payslips;
	}

	public void generatePayslip(List<Employee> employees, String destinationFile) {
		List<Payslip> payslips = run(employees);
		try {
			objectMapper.writeValue(new File(destinationFile), payslips);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void generatePayslip(InputStream employeeJsonFileinputStream, File destinationFile) {
		try {
			List<Employee> employees = objectMapper.readValue(employeeJsonFileinputStream, new TypeReference<List<Employee>>() {});
			List<Payslip> payslips = run(employees);
			objectMapper.writeValue(destinationFile, payslips);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	private Date getPaymentStartDate(int month) {
		Calendar currentPayrollStartDate = Calendar.getInstance();
		currentPayrollStartDate.set(Calendar.MONTH, month);
		currentPayrollStartDate.set(Calendar.DAY_OF_MONTH,
				currentPayrollStartDate.getActualMinimum(Calendar.DAY_OF_MONTH));
		return currentPayrollStartDate.getTime();
	}

	private Date getPaymentEndDate(int month) {
		Calendar currentPayrollEndDate = Calendar.getInstance();
		currentPayrollEndDate.set(Calendar.MONTH, month);
		// This is to set lenient correctly
		currentPayrollEndDate.set(Calendar.DAY_OF_MONTH, 1);
		currentPayrollEndDate.set(Calendar.DAY_OF_MONTH, currentPayrollEndDate.getActualMaximum(Calendar.DAY_OF_MONTH));
		return currentPayrollEndDate.getTime();
	}

}
