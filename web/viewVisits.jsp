<%-- 
    Document   : viewVisits
    Created on : Mar 18, 2013, 11:19:32 AM
    Author     : chrislalansingh
--%>

<%@page import="ece356.ProjectDBAO"%>
<%@page import="ece356.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Patient Visits</title>
    </head>
    <body>
        <h1>View Patient Visits</h1>
        <form method="post" action="StaffQueries?qnum=6">
            <p>       
                Select Patient to Update: 
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
            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>
