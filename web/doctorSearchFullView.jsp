<%-- 
    Document   : doctorSearchFullView
    Created on : 17-Mar-2013, 1:51:05 AM
    Author     : Leon Zhang
--%>


<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detailed information</title>
    </head>
    
    
    <body>
         <%
         String record = (String)request.getAttribute("record");
         int searchLength = (Integer)(session.getAttribute("searchLength"));
         int index = (Integer)(request.getAttribute("index"));
         out.println("<table border=1>");
         out.println("<tr><th>First Name</th><th>Last Name</th><th>PatientID</th><th>Date</th>" + 
                 "<th>Length</th><th>Diagnosis</th></tr>");                
         String splitRecords[] = record.split("#");
         String patientID = splitRecords[0];
         String date = splitRecords[1];
         String length = splitRecords[2];
         String diagnosis = splitRecords[3];
         session.setAttribute("patientID", patientID);
         session.setAttribute("searchDate", date);
         String firstName = splitRecords[4];
         String lastName = splitRecords[5];
         out.println("<tr><td>");
         out.print(firstName);
         out.print("</td><td>");
         out.print(lastName);
         out.print("</td><td>");
         out.print(patientID);
         out.print("</td><td>");
         out.print(date);
         out.print("</td><td>");
         out.print(length);
         out.print("</td><td>");
         out.print(diagnosis);
         out.println("</td></tr>");
         out.println("</table>");

         int previousRecord = index - 1;
         int nextRecord = index + 1;
         if(previousRecord >= 0) {
            out.println("<a href=\"DoctorSearchSorterServlet?index=" + previousRecord + "\">Previous record</a>");  
         }
         if(nextRecord <= searchLength - 1 ){
            out.println("<a href=\"DoctorSearchSorterServlet?index=" + nextRecord + "\">Next record</a>");
         }
         %>
         <br>
    <a href="Doctor.jsp">return to Doctor Home...</a>
    </body>
</html>
