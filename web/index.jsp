<%-- 
    Document   : index
    Created on : 11-Jan-2013, 9:14:06 AM
    Author     : Wojciech Golab
--%>
<%@page import="ece356.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospital Management Information System</title>
    </head>
    <body>
        <h1>Hospital Management Information System</h1>
        <% if (session.getAttribute("user") == null) { %>
        <form action= "AuthServlet" method = "post">

            <tr>
                <td>Username:</td>
                <td><input type = "text" name = "user"></td>
            </tr><br>
            <tr>
                <td>Password:</td>
                <td><input type = "password" name = "pass"></td>
            </tr><br>
            <tr>
                <td><input type = "submit" name = "submit" /></td>
            </tr>

        </form>
        <% } else { %>
        <% User user = (User)session.getAttribute("user");%>
        <p>Welcome <%= user.getName() %></p>
        <a href="LogoutServlet">Logout</a>
        <% } %>
    </body>
</html>
