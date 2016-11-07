/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipserv.hr.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipserv.hr.web.domain.Employee;
import com.shipserv.hr.web.rest.EmployeeServiceClient;

/**
 *
 * @author mpcariaso
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    
	@Autowired
	private EmployeeServiceClient serviceClient;
        
    
	public List<Employee> getEmployees(String status, String accessToken) {
        
    	return serviceClient.getEmployees(status, accessToken);
    }
    
	public Employee getEmployee(long employeeId, String accessToken) {
        
        return serviceClient.getEmployee(employeeId, accessToken);
    }
    
	public Employee addEmployee(Employee employee, String accessToken)  {
        
        return serviceClient.addEmployee(employee, accessToken);
    }
    
    
	public Employee updateEmployee(Employee employee, String accessToken)  {
        
        return serviceClient.updateEmployee(employee, accessToken);
    }
    
    
	public Employee deleteEmployee(long employeeId, String accessToken)  {
        
        return serviceClient.deleteEmployee(employeeId, accessToken);
    }   
    
}
