<%-- 
    Document   : Doctors
    Created on : 16-Mar-2013, 11:53:04 PM
    Author     : Leon Zhang
--%>

<%@page import="ece356.ProjectDBAO"%>
<%@page import="ece356.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctors</title>
    </head>
    <body>
        <h1>Doctors</h1>
        <% if (session.getAttribute("user") != null) { %>
            <% User user = (User)session.getAttribute("user");
            if (user.getType().equals("doctor")) {
            String doctorID = ProjectDBAO.getDoctorID(user.getID());
            session.setAttribute("doctorID", doctorID);
            %>
        <ul>
        <li><a href="createVisitationRecords.jsp">Create visitation record</a></li>
        <li><a href="searchDoctorRecords.jsp">Search records</a></li>     
        </ul>
        <% } else { %>
            <p>Only doctors have access to this page.</p>
            <% } %>
        <% } else { %>
        <p>Please log in to access this page.</p>
        <% } %>
        <a href="index.jsp">Home</a>
    </body>
</html>

