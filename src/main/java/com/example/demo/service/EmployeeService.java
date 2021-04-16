package com.example.demo.service;

import java.util.Collection;

import com.example.demo.model.Employee;

public interface EmployeeService {
	public Collection<Employee> getAllEmployee();
	public Employee getEmployeeById(Integer id);
	public Employee createEmployee(Employee employee);
	public Employee updateEmployee(Integer id,Employee employee);
}