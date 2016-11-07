package com.shipserv.hr.web.service;

import java.util.List;

import com.shipserv.hr.web.domain.Employee;

public interface EmployeeService {

	public List<Employee> getEmployees(String status, String accessToken);

	public Employee getEmployee(long employeeId, String accessToken);

	public Employee addEmployee(Employee employee, String accessToken);

	public Employee updateEmployee(Employee employee, String accessToken);

	public Employee deleteEmployee(long employeeId, String accessToken);

}