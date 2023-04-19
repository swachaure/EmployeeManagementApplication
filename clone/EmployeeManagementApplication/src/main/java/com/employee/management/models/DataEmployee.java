package com.employee.management.models;

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