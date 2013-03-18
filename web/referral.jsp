<%-- 
    Document   : referral
    Created on : Mar 17, 2013, 8:20:00 PM
    Author     : chrislalansingh
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="ece356.ProjectDBAO"%>
<%@page import="ece356.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Referral</title>
    </head>
    <body>
        <h1>Patient Referral</h1>
        <form method="post" action="StaffQueries?qnum=5">
            <p>       
                Select Patient to Refer: 
                <select name="patientID">
                    <%!ArrayList<String> patientList;%>
                    <%
                        User user = (User)session.getAttribute("user");
                        patientList = ProjectDBAO.getStaffPatients(user.getID());
                        String [] patientNames = new String [patientList.size()];
                        String [] patientIDs = new String [patientList.size()];
                        int i = 0;
                        for (String d : patientList) {
                            String [] s = d.split(",");
                            patientIDs[i] = s[0];
                            patientNames[i] = s[1] + " " + s[2];
                            i++;
                        }
                        
                        for (int j = 0; j < patientIDs.length; j++) {
                    %>
                    <option value="<%out.print(patientIDs[j]);%>"> <%out.print(patientNames[j]);%> </option>
                    <%
                        }
                    %>
                </select><br/>
                Refer to Doctor:
                <select name="doctorID">
                    <option value="0"> N/A </option>
                    <%!ArrayList<String> doctorList;%>
                    <%
                        doctorList = ProjectDBAO.getDoctors();
                        String [] doctorNames = new String [doctorList.size()];
                        String [] doctorIDs = new String [doctorList.size()];
                        int k = 0;
                        for (String d : doctorList) {
                            String [] s = d.split(",");
                            doctorIDs[k] = s[0];
                            doctorNames[k] = s[1] + " " + s[2];
                            k++;
                        }
                        
                        for (int j = 0; j < doctorIDs.length; j++) {
                    %>
                    <option value="<%out.print(doctorIDs[j]);%>"> <%out.print(doctorNames[j]);%> </option>
                    <%
                        }
                    %>
                </select><br/>
            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>
