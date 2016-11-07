/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipserv.hr.web.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shipserv.hr.web.domain.Employee;
import com.shipserv.hr.web.service.EmployeeService;

/**
 *
 * @author paolo
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
    @Autowired
    private EmployeeService employeeService;
    
    
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String getProducts(@RequestParam String status,
                                    Model model,
                                    HttpServletRequest request) throws SQLException {
        
        HttpSession session = request.getSession(false);
        
        if(session == null) {
          return "redirect:/login.html";  
        }
        
        String accessToken = (String) session.getAttribute("accessToken");
        
        logger.debug("Retrieving employees...");
        
        List<Employee> employees = employeeService.getEmployees(status, accessToken);
        
        
        for(Employee e: employees) {
            
           logger.debug("Employee Name: " + e.getName());            
        }        
        
        request.setAttribute("employees", employees);
        
        return "view-employees";
    }
    
    /**
     *
     * @param productId
     * @param request
     * @return
     * @throws SQLException
     */
    @RequestMapping(value="/id/{productId}", method = RequestMethod.GET)
    public ModelAndView getEmployee(@PathVariable("employeeId") int employeeId,
                                       HttpServletRequest request) throws SQLException {
        
        
        HttpSession session = request.getSession(false);
        ModelAndView model = null;
                
        if(session == null) {
            model = new ModelAndView("login");
            return model;
        } else {
            model = new ModelAndView("view-employees");
        }
        
        String accessToken = (String) session.getAttribute("accessToken");
        
        List<Employee> employees = new ArrayList<Employee>();
        Employee employee = employeeService.getEmployee(employeeId, accessToken);
        employees.add(employee);
        
        model.addObject("employees", employees);
        
        return model;       
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addEmployee(HttpServletRequest request) throws SQLException {
        
    	String name = request.getParameter("name");
    	String address = request.getParameter("address");
    	String contactNumber = request.getParameter("contactNumber");
    	
        HttpSession session = request.getSession(false);
        ModelAndView model = null;
        
        if(session == null) {
            model = new ModelAndView("login");
            return model;
        } else {
            model = new ModelAndView("view-employees");
        }
        
        String accessToken = (String) session.getAttribute("accessToken");
        
        Employee employee = new Employee();
        employee.setAddress(address);
        employee.setName(name);
        employee.setContactNumber(contactNumber);
        
        Employee addedEmployee = employeeService.addEmployee(employee, accessToken);
        
        List<Employee> employees = employeeService.getEmployees("ACTIVE", accessToken);
        
        
        model.addObject("employees", employees);
        
        return model;
    }
    
    @RequestMapping(value = "/update/preview/{id}/{name}/{address}/{contactNumber}/{status}", method = RequestMethod.GET)
    public ModelAndView updatePreviewEmployee(@PathVariable long id,
								    		  @PathVariable String name,
								    		  @PathVariable String address,
								    		  @PathVariable String contactNumber,
								    		  @PathVariable String status,
    										  HttpServletRequest request) throws SQLException {
        
    	    	
        HttpSession session = request.getSession(false);
        ModelAndView model = null;
        
        if(session == null) {
            model = new ModelAndView("login");
            return model;
        } else {
            model = new ModelAndView("update-employee");
        }
        
        Employee employee = new Employee();
        employee.setId(id);
        employee.setAddress(address);
        employee.setName(name);
        employee.setContactNumber(contactNumber);
        employee.setStatus(status);
                    
        model.addObject("employee", employee);
        
        return model;
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView updateEmployee(@PathVariable long id,
    								   HttpServletRequest request) throws SQLException {
        
    	 
    	String name = request.getParameter("name");
    	String address = request.getParameter("address");
    	String contactNumber = request.getParameter("contactNumber");
    	String status = request.getParameter("status");
    	
        HttpSession session = request.getSession(false);
        ModelAndView model = null;
        
        if(session == null) {
            model = new ModelAndView("login");
            return model;
        } else {
            model = new ModelAndView("view-employees");
        }
        
        String accessToken = (String) session.getAttribute("accessToken");
        
        Employee employee = new Employee();
        employee.setId(id);
        employee.setAddress(address);
        employee.setName(name);
        employee.setContactNumber(contactNumber);
        employee.setStatus(status);
        
        Employee updatedEmployee = employeeService.updateEmployee(employee, accessToken);
        
        List<Employee> employees = employeeService.getEmployees("ACTIVE", accessToken);
        
        
        model.addObject("employees", employees);
        
        return model;
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteEmployee( HttpServletRequest request) throws SQLException {
           	 
    	
        HttpSession session = request.getSession(false);
        ModelAndView model = null;
        
        if(session == null) {
            model = new ModelAndView("login");
            return model;
        } else {
            model = new ModelAndView("view-employees");
        }
        
        String accessToken = (String) session.getAttribute("accessToken");
        long id = Long.parseLong(request.getParameter("id"));
        
        employeeService.deleteEmployee(id, accessToken);        
        
        List<Employee> employees = employeeService.getEmployees("ACTIVE", accessToken);
        
        model.addObject("employees", employees);
        
        return model;
    }
   
}
