<%-- 
    Document   : allProcedures
    Created on : Mar 14, 2013, 9:32:39 PM
    Author     : Brandon
--%>

<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Hospital Procedures</title>
    </head>
    
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
    %>
    
    <body>
        <h1>All Hospital Procedures</h1>
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
                out.println("No procedures have been performed.");
            }
        %>
        <p>
            <a href="Finance.jsp">return to Finance Home...</a>
    </body>
</html>
