package com.example.restservice.controller;

import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.employeeDao.EmployeeDao;
import com.example.restservice.exception.EmployeeNotFoundException;
import com.example.restservice.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDao dao;

	@GetMapping(path = "/employees")
	public ResponseEntity<Object> getEmployees() {

				
				
				
		return new ResponseEntity<Object>(dao.getList(),HttpStatus.OK);
	}

	
	
	
	@GetMapping(path = "/employees/{id}")
	public EntityModel<Employee> getEmployee(@PathVariable int id) {

		Optional<Employee> empFound = dao.getList().stream().filter(i -> i.getEmpId() == id).findFirst();
		if (empFound.isPresent()) {
			
			
			EntityModel<Employee> resource = EntityModel.of(empFound.get());
			WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getEmployees());
			resource.add(link.withRel("all-emp"));
			return resource;
			//return new ResponseEntity<>(empFound.get(), HttpStatus.OK);
			
		}
			
		else
			throw new EmployeeNotFoundException("no user found");
	}

	
	@PostMapping(path = "/employees/")
	public ResponseEntity<String> addEmployee(@RequestBody @Valid Employee emp) {
		dao.addEmployee(emp);
		return new ResponseEntity<String>("added employee", HttpStatus.CREATED);

	}

	@DeleteMapping
	public ResponseEntity<String> deleteEmployee(@PathVariable int id)

	{
		Employee empFound = dao.getList().stream().filter(i -> i.getEmpId() == id).findFirst().get();
		if (empFound != null) {
			dao.getList().remove(empFound);
			return new ResponseEntity<>("Deleted employee with id " + id, HttpStatus.OK);
		} else
			throw new EmployeeNotFoundException("no user found for deletion");
	}

}
