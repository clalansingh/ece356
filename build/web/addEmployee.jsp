<%-- 
    Document   : addEmployee
    Created on : 30-Jan-2013, 8:50:09 PM
    Author     : Wojciech Golab
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ece356.Department"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Employee</title>
    </head>
    <body>
        <h1>Add Employee</h1>

        <form method="post" action="AddEmployeeServlet">
            Enter employee data:
            <p>       
                Employee ID: <input type="text" name="empID" size="20" autofocus><br/>
                Employee name: <input type="text" name="empName" size="20"><br/> 
                Job:<input type="text" name="job" size="20"><br/>
                Department:
                <select name="deptID">
                    <%! ArrayList<Department> departmentList;%>
                    <%
                        departmentList = (ArrayList<Department>) request.getAttribute("departmentList");
                        for (Department dep : departmentList) {
                    %>
                    <option value="<%= dep.getDeptID()%>"><%= dep.getDeptName()%></option>
                    <%
                        }
                    %>
                </select><br/>
                Salary: <input type="text" name="salary" size="20"><br/>
            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>
