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
        
        <table border="1">
            <tr>
                <th>Employee ID</th>
                <th>Name</th>
                <th>Address</th>
                <th>Contact Number</th>
                <th>Status</th>
                <th/>
            </tr>
           
            <c:forEach var="e" items="${employees}">

                <tr>
                    <td>${e.id}</td>
                    <td><a href="update/preview/${e.id}/${e.name}/${e.address}/${e.contactNumber}/${e.status}" value="${e.id}">${e.name}</a></td>
                    <td>${e.address}</td>
                    <td>${e.contactNumber}</td>
                    <td>
                        <c:choose>
                            <c:when test="${e.status == 'ACTIVE'}">
                                <font color="green">Currently Employed</font>
                            </c:when>
                            <c:otherwise>
                                <font color="red">Resigned</font>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                    	<form action="/hr-web/employee/delete" method="post">
                    		<input type="hidden" id="id" name="id" value="${e.id}" />                    	
                    		<input type="submit" value="Delete"/>
                    	</form>
                    </td>
                </tr>
            </c:forEach>        
        </table>
        <p>
            <a href="<%= getServletContext().getContextPath() %>/static/add-employee.html">Add Employee</a> 
        </p>
        
    </body>
</html>
