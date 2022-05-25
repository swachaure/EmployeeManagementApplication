package com.employee.management.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.management.models.DataEmployee;
import com.employee.management.models.Payslip;
import com.employee.management.models.TaxService;

@Service
public class PaySlipGeneratorService {

	@Autowired
	DataEmployee employeeData;
	
	@Autowired
	TaxService taxService;
	
	@Autowired
	Payslip paySlip;
	

	public ResponseEntity<String> generateEmployeeInfo(String jsonString) {
		JSONArray rsltdJsonArray = new JSONArray();
		try {
			JSONArray jsonArray = new JSONArray(jsonString);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				employeeData.setEmployeeFirstName(jsonObj.getString("firstName"));
				employeeData.setEmployeeSurname(jsonObj.getString("lastName"));
				paySlip.setSalary(jsonObj.getInt("annualSalary"));
				paySlip.setEmployeePaymentMonth(jsonObj.getInt("paymentMonth"));
				paySlip.setSuperRate(jsonObj.getDouble("superRate"));
				paySlip.CalculatePayMonth(jsonObj.getString("payment start date"));

				rsltdJsonArray.put(generateFinalJsonObject(jsonObj));
			}
			return ResponseEntity.status(HttpStatus.OK).body(rsltdJsonArray.toString());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rsltdJsonArray.toString());
		}
	}
	
	public JSONObject generateFinalJsonObject(JSONObject jsonObj) {

		JSONObject rsltdJson = new JSONObject();
		rsltdJson.put("employee", jsonObj);
		rsltdJson.put("fromDate", paySlip.getStartDate());
		rsltdJson.put("endDate", paySlip.getEndDate());
		rsltdJson.put("grossIncome", paySlip.CalculateGrossPay());
		rsltdJson.put("incomeTax", taxService.CalculateIncomeTax(paySlip.getSalary()));
		rsltdJson.put("superannuation", paySlip.CalculateSuper());
		rsltdJson.put("netIncome", paySlip.CalculateNetIncome(rsltdJson.getDouble("incomeTax")));

		return rsltdJson;
	}
}
