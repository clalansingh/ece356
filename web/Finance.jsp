<%-- 
    Document   : Finance
    Created on : Mar 12, 2013, 3:07:12 PM
    Author     : chrislalansingh
--%>
<%@page import="ece356.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Finance</title>
    </head>
    <body>
        <h1>Finance</h1>
        <% if (session.getAttribute("user") != null) { %>
            <% User user = (User)session.getAttribute("user");
            if (user.getType().equals("finance")) { %>
            <ul>
            <li><a href="monthlyProcedureSearch.jsp">Procedures performed each month</a></li>
            <li><a href="monthlyProcedureSearch2.jsp">Procedures performed each month per doctor</a></li> 
            <li><a href="FinanceQueries?qnum=3">View total number of procedures</a></li>        
            </ul>
            <% } else { %>
            <p>Only accountants have access to this page.</p>
            <% } %>
        <% } else { %>
        <p>Please log in to access this page.</p>
        <% } %>
        <a href="index.jsp">Home</a>
    </body>
</html>
