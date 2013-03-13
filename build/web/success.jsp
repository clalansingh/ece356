<%-- 
    Document   : index
    Created on : 11-Jan-2013, 9:14:06 AM
    Author     : Wojciech Golab
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ece356.Employee"%>
<%@page import="ece356.Department"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lab 2</title>
    </head>

    <%! ArrayList<Employee> employeeList;%>
    <%! ArrayList<Department> departmentList;%>
    <%! ArrayList employeeDepartmentList;%>
    <% employeeList = (ArrayList<Employee>) request.getAttribute("employeeList");%>
    <% departmentList = (ArrayList<Department>) request.getAttribute("departmentList");%>
    <% employeeDepartmentList = (ArrayList) request.getAttribute("employeeDepartmentList");%>

    <body>
        <h1>Success!</h1>
        <%
            if (employeeList != null) {
                out.println("<h1>Employee Data</h1>");
                out.println("<table border=1>");
                out.println("<tr><th>empID</th><th>empName</th><th>job</th><th>deptID</th><th>salary</th></tr>");
                for (Employee em : employeeList) {
                    out.println("<tr><td>");
                    out.print(em.getEmpID());
                    out.print("</td><td>");
                    out.print(em.getEmpName());
                    out.print("</td><td>");
                    out.print(em.getJob());
                    out.print("</td><td>");
                    out.print(em.getDeptID());
                    out.print("</td><td>");
                    out.print(em.getSalary());
                    out.println("</td></tr>");
                }
                out.println("</table>");
            }
        %>
        <p>
            <a href="index.jsp">return to main page...</a>
    </body>
</html>
