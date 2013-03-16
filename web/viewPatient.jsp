<%-- 
    Document   : viewPatient
    Created on : Mar 15, 2013, 8:13:21 PM
    Author     : Brandon
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="ece356.ProjectDBAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Patient</title>
    </head>
    <body>
        <h1>View Patient</h1>
        
        <form method="post" action="StaffQueries?qnum=3">
            <p>       
                Select Patient to View: 
                <select name="patientID">
                    <%!ArrayList<String> patientList;%>
                    <%
                        patientList = (ArrayList<String>) ProjectDBAO.getPatients();
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
            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>
