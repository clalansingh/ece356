<%-- 
    Document   : monthlyProcedures2
    Created on : Mar 14, 2013, 11:00:26 PM
    Author     : Brandon
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Monthly Procedures per Doctor</title>
    
    <%
        ArrayList <String> ret = (ArrayList<String>)request.getAttribute("ret");
        String [] doctor = new String[ret.size()];
        String [] operation = new String [ret.size()];
        String [] count = new String [ret.size()];
        int i = 0;
        String [] p;
        for (String s : ret)
        {
            p = s.split(",");
            doctor[i] = p[0];
            operation[i] = p[1];
            count[i] = p[2];
            i++;
        }
    %>
    
    <body>
        <h1>Monthly Procedures per Doctor</h1>
        <p><%out.print(request.getAttribute("month"));%>, <%out.print(request.getAttribute("year"));%></p>
        
        <%
            if (ret != null) {
                out.println("<table border=1>");
                out.println("<tr><th>Doctor</th><th>Operation</th><th>Count</th></tr>");
                for (int j = 0; j < operation.length; j++) {
                    out.println("<tr><td>");
                    out.print(doctor[j]);
                    out.print("</td><td>");
                    out.print(operation[j]);
                    out.print("</td><td>");
                    out.print(count[j]);
                    out.println("</td></tr>");
                }
                out.println("</table>");
            }
            else {
                out.println("No doctors performed any procedures this month");
            }
        %>
        <p>
            <a href="Finance.jsp">return to Finance Home...</a>
    </body>
</html>
