<%-- 
    Document   : addPatient
    Created on : Mar 15, 2013, 8:12:18 PM
    Author     : Brandon
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ece356.ProjectDBAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Patient</title>
    </head>
    <body>
        <h1>Add Patient</h1>
           <form method="post" action="StaffQueries?qnum=1">
            Enter employee data:
            <p>       
                Patient ID: <input type="text" name="patientID" size="20" autofocus><br/>
                First Name: <input type="text" name="firstName" size="20"><br/> 
                Last Name: <input type="text" name="lastName" size="20"><br/>
                Health Card: <input type="text" name="health_card" size="11"><br/>
                Social Insurance Number: <input type="text" name="SIN" size="11"><br/>
                Address: <input type="text" name="address" size="50"><br/>
                Phone Number: <input type="text" name="lastName" size="10"><br/> 
                Doctor:
                <select name="doctorID">
                    <%!ArrayList<String> doctorList;%>
                    <%
                        doctorList = ProjectDBAO.getDoctors();
                        String [] doctorNames = new String [doctorList.size()];
                        String [] doctorIDs = new String [doctorList.size()];
                        int i = 0;
                        for (String d : doctorList) {
                            String [] s = d.split(",");
                            doctorIDs[i] = s[0];
                            doctorNames[i] = s[1] + " " + s[2];
                            i++;
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
