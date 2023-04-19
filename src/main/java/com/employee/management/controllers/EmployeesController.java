package com.employee.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.services.PaySlipGeneratorService;

@RestController
@RequestMapping("/employee")
public class EmployeesController {

	@Autowired
	PaySlipGeneratorService generatorService;

	@PostMapping("/generatePaySlip")
	public ResponseEntity<String> generateEmployeeInfo(@RequestBody String jsonString) {
		return generatorService.generateEmployeeInfo(jsonString);
	}
}
