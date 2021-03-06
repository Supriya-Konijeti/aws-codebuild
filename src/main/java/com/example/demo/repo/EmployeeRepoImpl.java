package com.example.demo.repo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
@Component
public class EmployeeRepoImpl implements EmployeeRepo {
	private Map<Integer, Employee> employeeMap;
	{
		employeeMap=new HashMap<Integer, Employee>();
		employeeMap.put(1, new Employee(UUID.randomUUID().toString(), "John", "Doe", "john@email.com"));
		employeeMap.put(2, new Employee(UUID.randomUUID().toString(), "Marry", "Public", "marry@email.com"));
		employeeMap.put(3, new Employee(UUID.randomUUID().toString(), "Rahul", "Dravid", "rahul@email.com"));
		employeeMap.put(4, new Employee(UUID.randomUUID().toString(), "Sourav", "Ganguly", "sourav@email.com"));
	}
	@Override
	public Collection<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeMap.values();
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		Employee employee=employeeMap.get(id);
		if(employee==null)
		{
			throw new EmployeeNotFoundException("no such employee found");
		}
		return employee;
	}

	@Override
	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeMap.put(employeeMap.size()+1, employee);
		return employee;
	}

	@Override
	public Employee updateEmployee(Integer id,Employee employee) {
		Employee tempEmployee=employeeMap.get(id);
		if(tempEmployee==null)
		{
			throw new EmployeeNotFoundException("no employee found with the given id: "+id);
		}
		tempEmployee.setFirstName(employee.getFirstName());
		tempEmployee.setLastName(employee.getLastName());
		tempEmployee.setEmail(employee.getEmail());
		employeeMap.remove(id);
		employeeMap.put(id, tempEmployee);
		return tempEmployee;
	}

}