<%-- 
    Document   : doctorSearchListView
    Created on : 17-Mar-2013, 1:50:50 AM
    Author     : Leon Zhang
--%>


<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Matching records</title>
    </head>  

    
    <body>
         <%
         ArrayList <String> searchResults = (ArrayList<String>)request.getAttribute("ret");
         session.setAttribute("searchResults", searchResults);
         if (searchResults != null) {
            out.println("<table border=1>");
            out.println("<tr><th>First Name</th><th>Last Name</th><th>Date</th><th>Full view</th></tr>");
            int index = 0;
            for (String record : searchResults) {
                String splitRecords[] = record.split("#");
                String date = splitRecords[1];
                String firstName = splitRecords[4];
                String lastName = splitRecords[5];
                out.println("<tr><td>");
                out.print(firstName);
                out.print("</td><td>");
                out.print(lastName);
                out.print("</td><td>");
                out.print(date);
                out.print("</td><td>");
                out.print("<a href=\"DoctorSearchServlet?index=");
                out.print(index + "\"");
                out.print(".jsp\">See full information</a>");
                out.println("</td></tr>");
                index++;
            }
            out.println("</table>");
         } else {
            out.println("No visit records found.");
         }
         %>
    <a href="Doctor.jsp">return to Doctor Home...</a>
    </body>
</html>
