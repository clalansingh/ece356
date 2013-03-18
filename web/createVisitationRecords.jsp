<%-- 
    Document   : createVisitationRecords
    Created on : 16-Mar-2013, 11:55:07 PM
    Author     : Leon Zhang
--%>
<%@page import="java.util.ArrayList"%>
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
                Patient ID: <input type="number" name="patientID" size="20" autofocus><br/>
                Date: <input type="date" name="date" size="20"><br/> 
                Length:<input type="number" name="length" size="20"><br/>
                Diagnosis:<input type="text" name="diagnosis" size="20"><br/>
               

                Comments: <textarea name="comments" cols="25" rows="5"></textarea><br>

            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>

