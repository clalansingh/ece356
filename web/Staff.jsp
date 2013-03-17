<%-- 
    Document   : Staff
    Created on : Mar 15, 2013, 8:09:44 PM
    Author     : Brandon
--%>
<%@page import="ece356.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff</title>
    </head>
    <body>
        <h1>Staff</h1>
        <% if (session.getAttribute("user") != null) { %>
            <% User user = (User)session.getAttribute("user");
            if (user.getType().equals("staff")) { %>
            <ul>
            <li><a href="addPatient.jsp">Add patient</a></li>
            <li><a href="updatePatient.jsp">Update patient</a></li> 
            <li><a href="viewPatient.jsp">View patient</a></li>        
            </ul>
            <% } %>
        <% } else { %>
        <p>Please log in to access this page.</p>
        <% } %>
    </body>
</html>