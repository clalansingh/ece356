<%-- 
    Document   : createPatientSuccess
    Created on : Mar 17, 2013, 3:51:33 PM
    Author     : chrislalansingh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient created successfully</title>
    </head>
    <body>
        <h1>Patient created successfully!</h1>
        <p><%= request.getAttribute("num") %> rows effected</p>
        <a href="Staff.jsp">Return to Staff Tools.</a>
    </body>
</html>
