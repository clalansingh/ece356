<%-- 
    Document   : updatePatientForm
    Created on : Mar 17, 2013, 5:54:12 PM
    Author     : chrislalansingh
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="ece356.ProjectDBAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Update Patient</h1>
        <form method="post" action="StaffQueries?qnum=4&patientID=<%= request.getAttribute("patientID") %>">
            Enter patient data:
            <p>       
                First Name: <input type="text" name="firstName" size="20">Current value: <%= request.getAttribute("firstName") %><br/>
                Last Name: <input type="text" name="lastName" size="20">Current value: <%= request.getAttribute("lastName") %><br/>
                Health Card: <input type="text" name="health_card" size="11">Current value: <%= request.getAttribute("health_card") %><br/>
                Social Insurance Number: <input type="text" name="SIN" size="11">Current value: <%= request.getAttribute("SIN") %><br/>
                Address: <input type="text" name="address" size="50">Current value: <%= request.getAttribute("address") %><br/>
                Phone Number: <input type="text" name="phone" size="10">Current value: <%= request.getAttribute("phone") %><br/>
            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>
