package com.example.demo.repo;

import java.util.Collection;

import com.example.demo.model.Employee;

public interface EmployeeRepo {
	
	public Collection<Employee> getAllEmployee();
	public Employee getEmployeeById(Integer id);
	
	public Employee createEmployee(Employee employee);
	public Employee updateEmployee(Integer id,Employee employee);

}