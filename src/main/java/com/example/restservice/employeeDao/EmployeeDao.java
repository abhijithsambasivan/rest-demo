package com.example.restservice.employeeDao;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.restservice.exception.EmployeeAlreadyPresentException;
import com.example.restservice.exception.EmployeeNotFoundException;
import com.example.restservice.model.Employee;

@Service

public class EmployeeDao {

	static  ArrayList<Employee> listEmp = new ArrayList<Employee>();
	
	
	public void addEmployee(Employee emp) {
	Optional<Employee> alreadyPresent = 	listEmp.stream().filter(i->i.getEmpId() == emp.getEmpId()).findAny();
	if(! alreadyPresent.isPresent())
		listEmp.add(emp);
	else
		throw new EmployeeAlreadyPresentException("Employee already exists for the id");
	}
	
	public void deleteEmployee(Employee emp)
	{
	Optional<Employee> match =	listEmp.stream().filter(i ->i.getEmpId() == emp.getEmpId() ).findFirst();
		if(match.isPresent())
		{
		listEmp.remove(match.get());
		}
		else {
			throw new EmployeeNotFoundException("Employee not found;unable to delete");
		}
	}
	

	public ArrayList<Employee> getList() {
		return listEmp;
	}
	
	@PostConstruct
	public ArrayList<Employee> initialiseList() {

		Employee e1 = new Employee();
		e1.setEmpId(1);
		e1.setEmpName("Ram");
		e1.setDesignation("ITA");

		Employee e2 = new Employee();
		e2.setEmpId(2);
		e2.setEmpName("Rakesh");
		e2.setDesignation("ITA");

		Employee e3 = new Employee();
		e3.setEmpId(3);
		e3.setEmpName("Raju");
		e3.setDesignation("SE");

		Employee e4 = new Employee();
		e4.setEmpId(4);
		e4.setEmpName("Adam");
		e4.setDesignation("AST");

		listEmp.add(e1);

		listEmp.add(e2);

		listEmp.add(e3);

		listEmp.add(e4);

		return listEmp;
	}

}
