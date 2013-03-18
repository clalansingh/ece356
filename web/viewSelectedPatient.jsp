<%-- 
    Document   : viewSelectedPatient
    Created on : Mar 17, 2013, 8:48:14 PM
    Author     : chrislalansingh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Selected Patient</title>
    </head>
    <body>
        <h1>View Patient</h1>
        <table border="1">
            <tr>
                <td>Patient ID</td>
                <td><%= request.getAttribute("patientID") %></td>
            </tr>
            <tr>
                <td>First Name</td>
                <td><%= request.getAttribute("firstName") %></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><%= request.getAttribute("lastName") %></td>
            </tr>
            <tr>
                <td>Health Card #</td>
                <td><%= request.getAttribute("health_card") %></td>
            </tr>
            <tr>
                <td>SIN #</td>
                <td><%= request.getAttribute("SIN") %></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><%= request.getAttribute("address") %></td>
            </tr>
            <tr>
                <td>Phone #</td>
                <td><%= request.getAttribute("phone") %></td>
            </tr>
        </table>
            <br/><a href="Staff.jsp">Return to Staff Tools page</a>
    </body>
</html>
