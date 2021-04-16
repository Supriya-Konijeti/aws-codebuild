package com.example.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.ErrorResponse;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class Employeecontroller {
	private EmployeeService employeeService;

	@Autowired
	public Employeecontroller(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleError(EmployeeNotFoundException e)
	{
		ErrorResponse response=new ErrorResponse();
		response.setErrorMessage(e.getMessage());
		response.setErrorReportTime(System.currentTimeMillis());
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	@GetMapping(path = "/employees", produces = "application/json")
	public ResponseEntity<Collection<Employee>> findAllEmployee() {

		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployee());
	}
	
	@GetMapping(path = "/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(id));
	}
	@PostMapping(path = "/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employee));
	}
	
	@PutMapping(path = "/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id,@RequestBody Employee employee)
	{
		Employee e=employeeService.updateEmployee(id, employee);
		return ResponseEntity.status(HttpStatus.OK).body(e);
	}
}