<%-- 
    Document   : add-product
    Created on : Nov 23, 2015, 2:34:31 AM
    Author     : paolo
--%>

<%@page import="com.grocerific.service.ProductService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.grocerific.domain.Product" %>
<%@page errorPage="error.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>
    <body>       
        
        <jsp:useBean id="newProduct" class="com.grocerific.domain.Product" scope="request"/>
        <jsp:setProperty name="newProduct" property="id" param="productId"/>
        <jsp:setProperty name="newProduct" property="code" param="code"/>
        <jsp:setProperty name="newProduct" property="size" param="size"/>
        <jsp:setProperty name="newProduct" property="price" param="price"/>
        <jsp:setProperty name="newProduct" property="stock" param="stock"/>
        <jsp:setProperty name="newProduct" property="category" param="category"/>
        
        <%
            if(ProductsService.isLoaded()) ProductsService.addProduct(newProduct);
           
            request.setAttribute("products", ProductsService.getProducts()); 
            RequestDispatcher rd = request.getRequestDispatcher("view-products.jsp");
            rd.forward(request, response);
        %>
    </body>
</html>
