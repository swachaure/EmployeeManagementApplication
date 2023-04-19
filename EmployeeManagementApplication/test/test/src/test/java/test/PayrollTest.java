package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;
import com.google.common.collect.Lists;

import test.payroll.Employee;
import test.payroll.Payroll;
import test.payroll.Payslip;
import test.payroll.TaxCaculator;

public class PayrollTest {
	private ObjectMapper objectMapper = new ObjectMapper();
	private SimpleDateFormat df = new SimpleDateFormat("dd MMMMM");


	@Test
	public void testTaxCalculator() {
		BigDecimal taxIncome18000Less = TaxCaculator.calculateTax(BigDecimal.valueOf(1000)).get();
		assertTrue(taxIncome18000Less.compareTo(BigDecimal.valueOf(0.0)) == 0);

		BigDecimal taxIncome18000 = TaxCaculator.calculateTax(BigDecimal.valueOf(18000)).get();
		assertTrue(taxIncome18000.compareTo(BigDecimal.valueOf(0.0)) == 0);

		BigDecimal taxIncome18201 = TaxCaculator.calculateTax(BigDecimal.valueOf(18201)).get();
		assertTrue(taxIncome18201.compareTo(BigDecimal.valueOf(0.19)) == 0);

		BigDecimal taxIncome37000 = TaxCaculator.calculateTax(BigDecimal.valueOf(37000)).get();
		assertTrue(taxIncome37000.compareTo(BigDecimal.valueOf(37000 - 18200).multiply(BigDecimal.valueOf(0.19))) == 0);

		BigDecimal taxIncome37001 = TaxCaculator.calculateTax(BigDecimal.valueOf(37001)).get();
		assertTrue(taxIncome37001.compareTo(BigDecimal.valueOf(3572)
				.add(BigDecimal.valueOf(37001 - 37000).multiply(BigDecimal.valueOf(0.325)))) == 0);

		BigDecimal taxIncome87000 = TaxCaculator.calculateTax(BigDecimal.valueOf(87000)).get();
		assertTrue(taxIncome87000.compareTo(BigDecimal.valueOf(3572)
				.add(BigDecimal.valueOf(87000 - 37000).multiply(BigDecimal.valueOf(0.325)))) == 0);

		BigDecimal taxIncome18000Over = TaxCaculator.calculateTax(BigDecimal.valueOf(180999)).get();
		assertTrue(taxIncome18000Over.compareTo(BigDecimal.valueOf(54232)
				.add(BigDecimal.valueOf(180999 - 180000).multiply(BigDecimal.valueOf(0.45)))) == 0);
	}

	@Test
	public void testPayroll() {
		Payroll payroll = new Payroll();
		List<Payslip> payslips = payroll.run(Lists.newArrayList(
				new Employee("David", "Rudd", 60050, 0.09, GregorianCalendar.FEBRUARY),
				new Employee("Ryan", "Chen", 120000, 0.1, GregorianCalendar.NOVEMBER)));
		
		int testedPayslips = 0;
		assertEquals("Paylips should have 2 items", 2, payslips.size());
		
		for (Payslip payslip: payslips) {
			if (payslip.getEmployee().getFirstName().equals("David")) {
				assertTrue(df.format(payslip.getFromDate()).equals("01 February"));
				assertTrue(df.format(payslip.getToDate()).equals("28 February"));
				assertEquals(payslip.getIncomeTax(), BigDecimal.valueOf(922));
				assertEquals(payslip.getNetIncome(), BigDecimal.valueOf(4082));
				testedPayslips ++;
			}
			
			else if (payslip.getEmployee().getFirstName().equals("Ryan")) {
				assertTrue(df.format(payslip.getFromDate()).equals("01 November"));
				assertTrue(df.format(payslip.getToDate()).equals("30 November"));
				assertEquals(payslip.getIncomeTax(), BigDecimal.valueOf(2669));
				assertEquals(payslip.getNetIncome(), BigDecimal.valueOf(7331));
				
				testedPayslips ++;
			}
		}
		
		assertEquals("2 payslips should be tested", payslips.size(), testedPayslips);
		
	}

	@Test
	public void testGeneratePayslip() throws JsonParseException, JsonMappingException, IOException{
		Payroll payroll = new Payroll();
				
		ClassLoader classLoader = getClass().getClassLoader();
		File desFile = new File("target/payslips.json");
		payroll.generatePayslip(classLoader.getResourceAsStream("employees.json"), desFile);
		
		JsonNode beforeNode = objectMapper.readTree(classLoader.getResourceAsStream("expected-payslips.json"));
		JsonNode afterNode = objectMapper.readTree(desFile);
	
		JsonNode patch = JsonDiff.asJson(beforeNode, afterNode);
		String diffs = patch.toString();
		assertEquals("No difference should be found", "[]", diffs);
		
	}
	
	

}
