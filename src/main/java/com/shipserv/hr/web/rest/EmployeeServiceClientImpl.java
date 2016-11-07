package com.shipserv.hr.web.rest;

import static com.jayway.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jayway.restassured.response.ValidatableResponse;
import com.shipserv.hr.web.domain.Employee;

@Service
public class EmployeeServiceClientImpl implements EmployeeServiceClient {
	
	private String KEYSTORE_CERTIFICATE_FILE = "D:\\apache-tomcat-8.0.30\\conf\\tomcat.jks";
	private String KEYSTORE_KEYPASS = "tomcat";
	private String BASE_SSL_SERVICE_URL = "https://localhost";
	private String SERVICE_CONTEXT_URI = "/hr-service/protected";
	private int BASE_SSL_SERVICE_PORT = 8443;
	
	
	public List<Employee> getEmployees(String status, String accessToken) {
        
				
		ValidatableResponse response =
				
		given().
			keystore(KEYSTORE_CERTIFICATE_FILE, KEYSTORE_KEYPASS).
			formParam("status", status).
			header("Authorization", "Bearer " + accessToken.trim()).
			header("Accept","application/vnd.shipserv.hr+json").
			baseUri(BASE_SSL_SERVICE_URL). //for SSL request
			port(BASE_SSL_SERVICE_PORT). //for SSL request
			log().all().
		when().
			//get("http://localhost:8080/test01/protected/messaging/{name}").
			get(SERVICE_CONTEXT_URI + "/employee/list").
		
		then().
			log().all();
    	
		List<Employee> employees = Arrays.asList(response.extract().body().as(Employee[].class));
    	
	    return employees;
    }
    
    
	public Employee getEmployee(long employeeId, String accessToken)  {
        
    	ValidatableResponse response =
    			
    	    	given().
    				keystore(KEYSTORE_CERTIFICATE_FILE, KEYSTORE_KEYPASS).
    				pathParam("id", employeeId).    				
    				header("Authorization", "Bearer " + accessToken.trim()).
    				header("Accept","application/vnd.shipserv.hr+json").
    				baseUri(BASE_SSL_SERVICE_URL). //for SSL request
    				port(BASE_SSL_SERVICE_PORT). //for SSL request
    				log().all().
    			when().
    				//get("http://localhost:8080/test01/protected/messaging/{name}").
    				get(SERVICE_CONTEXT_URI + "/employee/{id}").
    			
    			then().
    				log().all();
    	    	
    	    	Employee employee = response.extract().body().as(Employee.class);
    		    	
    		    return employee;
    }
    
    
	public Employee addEmployee(Employee employee, String accessToken)  {
        
    	ValidatableResponse response =
    			
    	given().
			keystore(KEYSTORE_CERTIFICATE_FILE, KEYSTORE_KEYPASS).
			formParam("name", employee.getName()).
			formParam("address", employee.getAddress()).
			formParam("contactNumber", employee.getContactNumber()).
			header("Authorization", "Bearer " + accessToken.trim()).
			header("Accept","application/vnd.shipserv.hr+json").
			baseUri(BASE_SSL_SERVICE_URL). //for SSL request
			port(BASE_SSL_SERVICE_PORT). //for SSL request
			log().all().
		when().
			//get("http://localhost:8080/test01/protected/messaging/{name}").
			post(SERVICE_CONTEXT_URI + "/employee/add").
		
		then().
			log().all();
    	
    	Employee addedEmployee = response.extract().body().as(Employee.class);
	    	
	    return addedEmployee;
	    
	}
    
    
	public Employee updateEmployee(Employee employee, String accessToken)  {
        
    	ValidatableResponse response =
    			
    	given().
		keystore("D:\\apache-tomcat-8.0.30\\conf\\tomcat.jks", "tomcat").
		formParam("id", employee.getId()).
		formParam("name", employee.getName()).
		formParam("address", employee.getAddress()).
		formParam("contactNumber", employee.getContactNumber()).
		formParam("status", employee.getStatus()).
		header("Authorization", "Bearer " + accessToken.trim()).
		header("Accept","application/vnd.shipserv.hr+json").
		baseUri("https://localhost"). //for SSL request
		port(8443). //for SSL request
		log().all().
	when().
		post(SERVICE_CONTEXT_URI + "/employee/update").		
	then().
		log().all();
    	
    	Employee updatedEmployee = response.extract().body().as(Employee.class);
    	
	    return updatedEmployee;
    }
    
    
	public Employee deleteEmployee(long employeeId, String accessToken)  {
        
		ValidatableResponse response =
    			
		    	given().
				keystore("D:\\apache-tomcat-8.0.30\\conf\\tomcat.jks", "tomcat").
				pathParam("id", employeeId).
				header("Authorization", "Bearer " + accessToken.trim()).
				header("Accept","application/vnd.shipserv.hr+json").
				baseUri("https://localhost"). //for SSL request
				port(8443). //for SSL request
				log().all().
			when().
				delete(SERVICE_CONTEXT_URI + "/employee/delete/{id}").		
			then().
				log().all();
		    	
		    	Employee deletedEmployee = response.extract().body().as(Employee.class);
		    	
			    return deletedEmployee;
    }

}
