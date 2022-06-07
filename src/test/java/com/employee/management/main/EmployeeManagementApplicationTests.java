package com.employee.management.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employee.management.controllers.EmployeesController;
import com.employee.management.services.PaySlipGeneratorService;

@SpringBootTest
class EmployeeManagementApplicationTests {

	@Autowired
	PaySlipGeneratorService generatorService;

	@Autowired
	EmployeesController employeeController;

	@Test
	void contextLoads() {
	}

	@Test
	void calculatePaySlipWithValidJSON() {

		String jsonString = "[\n" + "  {\n" + "    \"firstName\": \"David\",\n" + "    \"lastName\": \"Rudd\",\n"
				+ "    \"annualSalary\": 60050,\n" + "    \"paymentMonth\": 1,\n"
				+ "    \"payment start date\": January,\n" + "    \"superRate\": 0.09\n" + "  },\n" + "  {\n"
				+ "    \"firstName\": \"Ryan\",\n" + "    \"lastName\": \"Chen\",\n" + "    \"annualSalary\": 120000,\n"
				+ "    \"paymentMonth\": 1,\n" + "    \"payment start date\": February,\n" + "    \"superRate\": 0.1\n"
				+ "  }\n" + "]";

		ResponseEntity<String> rspns = employeeController.generateEmployeeInfo(jsonString);
		assertEquals(rspns.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void calculatePaySlipWithInValidJSON() {

		String jsonString = "nulll;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;";

		ResponseEntity<String> rspns = employeeController.generateEmployeeInfo(jsonString);
		assertEquals(rspns.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
}
