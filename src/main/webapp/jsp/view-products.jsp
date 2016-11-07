<%-- 
    Document   : view-products
    Created on : Nov 23, 2015, 2:06:56 AM
    Author     : paolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.grocerific.domain.Product" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Products</title>
    </head>
    <body>        
        
        <%--EXERCISE 10--%>
        <h3>Welcome, ${fullName}</h3>        
        <h3>You are logged in as: ${userName}</h3>        
        
        <p>
        <a href="<%= getServletContext().getContextPath() %>/logout">logout</a>          
        </p>
        
        <table border="1">
            <tr>
                <th>Product ID</th>
                <th>Product Code</th>
                <th>Price</th>
                <th>Size</th>
                <th>Availability</th> 
                <th>Category</th>
            </tr>
           
            <c:forEach var="p" items="${products}">

                <tr>
                    <td>${p.id}</td>
                    <td><a href="id/${p.id}" value="${p.id}">${p.code}</a></td>
                    <td>${p.price}</td>
                    <td>${p.size}</td>
                    <td>
                        <c:choose>
                            <c:when test="${p.stock > 0}">
                                <font color="green">In Stock</font>
                            </c:when>
                            <c:otherwise>
                                <font color="red">Out of Stock</font>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${p.category}</td>
                </tr>
            </c:forEach>        
        </table>
        <p>
            <a href="<%= getServletContext().getContextPath() %>/static/add-product.html">Add Product</a> 
        </p>
        
    </body>
</html>
