<%-- 
    Document   : monthlyProcedures
    Created on : Mar 13, 2013, 11:04:18 AM
    Author     : Brandon
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="ece356.Procedure"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Monthly Procedures</title>
    
    <%
        ArrayList <String> ret = (ArrayList<String>)request.getAttribute("ret");
        String [] operation = new String [ret.size()];
        String [] count = new String [ret.size()];
        int i = 0;
        String [] p;
        for (String s : ret)
        {
            p = s.split(",");
            operation[i] = p[0];
            count[i] = p[1];
            i++;
        }
        
        //sort by descending order
        //for (int j = 0; j < )
    %>
    
    <body>
        <h1>Monthly Procedures</h1>
        <p><%out.print(request.getAttribute("month"));%>, <%out.print(request.getAttribute("year"));%></p>
        
        <%
            if (ret != null) {
                out.println("<table border=1>");
                out.println("<tr><th>Operation</th><th>Count</th></tr>");
                for (int j = 0; j < operation.length; j++) {
                    out.println("<tr><td>");
                    out.print(operation[j]);
                    out.print("</td><td>");
                    out.print(count[j]);
                    out.println("</td></tr>");
                }
                out.println("</table>");
            }
            else {
                out.println("No procedures performed this month.");
            }
        %>
        <p>
            <a href="Finance.jsp">return to Finance Home...</a>
    </body>
</html>
