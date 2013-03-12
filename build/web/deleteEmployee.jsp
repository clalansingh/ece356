<%-- 
    Document   : deleteEmployee
    Created on : Feb 6, 2013, 10:18:22 AM
    Author     : chrislalansingh
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ece356.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Employee</title>
    </head>
    <body>
        <h1>Delete Employee</h1>

        <form method="post" action="DeleteEmployeeServlet">
            Select an employee:
            <p>       
                Employee:
                <select name="empID">
                    <%! ArrayList<Employee> employeeList;%>
                    <%
                        employeeList = (ArrayList<Employee>) request.getAttribute("employeeList");
                        for (Employee emp : employeeList) {
                    %>
                    <option value="<%= emp.getEmpID()%>"><%= emp.getEmpName()%></option>
                    <%
                        }
                    %>
                </select><br/>
            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>
