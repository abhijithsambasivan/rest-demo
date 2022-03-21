package com.example.restservice.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Employee {
	
	
	//min is used for int
	//use size for string
	@Min(value = 0)
	
	private long empId;
	@Size(min =2 ,message = "employee name should be greater than 2 characters")
	private String empName;
	@NotBlank(message = "Designation should not be null")
	private String designation;

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}
	

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", designation=" + designation + "]";
	}

}
