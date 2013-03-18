<%-- 
    Document   : searchDoctorRecords
    Created on : 16-Mar-2013, 11:55:27 PM
    Author     : Leon Zhang
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search records</title>
    </head>
    <body>
        <h1>Search records</h1>

        <form method="post" action="DoctorsQueries?qnum=2">
            Enter visit data:
            <p> 
                Patient First Name: <input type="text" name="firstName" size="20" autofocus><br/>
                Patient Last Name: <input type="text" name="lastName" size="20" autofocus><br/>
                Patient ID: <input type="number" name="patientID" size="20" autofocus><br/>
                Date: <input type="date" name="date" size="20"><br/> 
                Length:<input type="number" name="length" size="20"><br/>
                Diagnosis:<input type="text" name="diagnosis" size="20"><br/>
                Select treatment type:
                <select name="treatmentType">
                    <option value="prescription">Prescription</option>
                    <option value="operation">Operation</option>
                    <option value="referralID">Referral ID</option>
                </select></br>
                Treatment keywords:<input type="text" name="treatment" size="20"><br/>     
                Comments: <textarea name="comments" cols="25" rows="5"></textarea><br>              

            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>
