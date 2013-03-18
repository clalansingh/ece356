<%-- 
    Document   : viewSelectedVisits
    Created on : Mar 18, 2013, 1:56:04 PM
    Author     : chrislalansingh
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ece356.ProjectDBAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Selected Visits</title>
    </head>
    <body>
        <% ArrayList<String> patient = ProjectDBAO.getPatient((String)request.getAttribute("patientID")); %>
        <h1>View Visits for <%= patient.get(0) %> <%= patient.get(1) %></h1>
        <table border="1">
            <tr>
                <td>Date</td>
                <td>Length</td>
                <td>Diagnosis</td>
                <td>Comments</td>
            </tr>
            <% 
            ArrayList<String> visits = (ArrayList<String>)request.getAttribute("visits");
            int i = 0;
            for (String d : visits) {
                String [] s = d.split(",");
                %>
                <tr>
                    <td><%= s[0] %></td>
                    <td><%= s[1] %></td>
                    <td><%= s[2] %></td>
                    <td><%= s[3] %></td>
                </tr>
                <%
                i++;
            }
            %>
        </table>
        <br/><a href="Staff.jsp">Return to Staff Tools page</a>
    </body>
</html>
