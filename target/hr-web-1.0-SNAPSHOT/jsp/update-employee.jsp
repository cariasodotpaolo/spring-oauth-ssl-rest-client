<%-- 
    Document   : view-products
    Created on : Nov 23, 2015, 2:06:56 AM
    Author     : paolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.shipserv.hr.web.domain.Employee" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Employees</title>
    </head>
    <body>        
        
        <h1>Shipserv HR</h1>
        <p/>        
        <h3>Welcome! You are logged in as: ${userName}</h3>        
        
        <p>
        <a href="<%= getServletContext().getContextPath() %>/logout">logout</a>          
        </p>
        
        <div>
            <h1>Update Employee</h1>
                <form action="/hr-web/employee/update/${employee.id}" method="post">
                
                <table>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" id="name" value="${employee.name}"/></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" name="address" id="address" value="${employee.address}"/></td>
                    </tr>
                    <tr>
                        <td>Contact Number</td>
                        <td><input type="text" name="contactNumber" id="contactNumber" value="${employee.contactNumber}"/></td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td>
                            <select name="status" id="status">
                                <option value="ACTIVE">ACTIVE</option>
                                <option value="INACTIVE">INACTIVE</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Update"/></td>
                    </tr>                    
                </table>
                
            </form>
            
        </div>
        
        
    </body>
</html>
