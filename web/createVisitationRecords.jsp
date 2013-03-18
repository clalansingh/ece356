<%-- 
    Document   : createVisitationRecords
    Created on : 16-Mar-2013, 11:55:07 PM
    Author     : Leon Zhang
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="ece356.ProjectDBAO"%>
<%@page import="ece356.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create visitation record</title>
    </head>
    <body>
        <h1>Create record</h1>

        <form method="post" action="DoctorsQueries?qnum=1">
            Enter visit data:
            <p>       
                Patient: <select name="patientID">
                    <%!ArrayList<String> patientList;%>
                    <%
                        User user = (User)session.getAttribute("user");
                        patientList = ProjectDBAO.getDoctorPatients(user.getID());
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
                Date: <input type="date" name="date" size="20"><br/> 
                Length:<input type="number" name="length" size="20"><br/>
                Diagnosis:<input type="text" name="diagnosis" size="20"><br/>
               

                Comments: <textarea name="comments" cols="25" rows="5"></textarea><br>

            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>

