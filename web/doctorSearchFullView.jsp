<%-- 
    Document   : doctorSearchFullView
    Created on : 17-Mar-2013, 1:51:05 AM
    Author     : Leon Zhang
--%>


<%@page import="ece356.ProjectDBAO"%>
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
                 "<th>Length</th><th>Diagnosis</th><th>Comments</th></tr>");                
         String splitRecords[] = record.split("#");
         String patientID = splitRecords[0];
         String date = splitRecords[1];
         String length = splitRecords[2];
         String diagnosis = splitRecords[3];
         session.setAttribute("patientID", patientID);
         session.setAttribute("searchDate", date);
         String firstName = splitRecords[4];
         String lastName = splitRecords[5];
         String comments = splitRecords[6];
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
         out.print("</td><td>");
         out.print(comments);
         out.println("</td></tr>");
         out.println("</table>");
         %>        
         <br>
         Prescriptions:
         <table border=1>
         <tr><th>Drug name</th><th>Drug Dosage</th><th>Date of prescription</th></tr>
         <%
         ArrayList<String> prescriptions = ProjectDBAO.queryPrescriptionsForPatient(Integer.parseInt(patientID));
         for(String prescription: prescriptions) {
             String[] splitPrescription = prescription.split("#");
             String drug = splitPrescription[0];
             String dosage = splitPrescription[1];
             String prescribedDate = splitPrescription[2];
             out.println("<tr><td>");
             out.print(drug);
             out.print("</td><td>");
             out.print(dosage);
             out.print("</td><td>");
             out.print(prescribedDate);
             out.println("</td></tr>");             
         }
         %>
         </table>
         <br>
         Procedures:
         <table border=1>
         <tr><th>Operation name</th><th>Date of operation</th></tr>
         <%
         ArrayList<String> procedures = ProjectDBAO.queryProceduresForPatient(Integer.parseInt(patientID));
         for(String procedure: procedures) {
             String[] splitProcedure = procedure.split("#");
             String operation = splitProcedure[0];
             String operationDate = splitProcedure[1];
             out.println("<tr><td>");
             out.print(operation);
             out.print("</td><td>");
             out.print(operationDate);
             out.println("</td></tr>");             
         }
         %>
         </table>
         <br>
         Referrals:
         <table border=1>
         <tr><th>ReferralID</th><th>Date of Referral</th></tr>
         <%
         ArrayList<String> referrals = ProjectDBAO.queryReferralsForPatient(Integer.parseInt(patientID));
         for(String referral: referrals) {
             String[] splitReferral = referral.split("#");
             String referralID = splitReferral[0];
             String referralDate = splitReferral[1];
             out.println("<tr><td>");
             out.print(referralID);
             out.print("</td><td>");
             out.print(referralDate);
             out.println("</td></tr>");             
         }  
         out.print("</table>");
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
