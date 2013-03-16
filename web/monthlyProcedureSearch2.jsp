<%-- 
    Document   : monthlyProcedureSearch2
    Created on : Mar 14, 2013, 10:05:33 PM
    Author     : Brandon
--%>

<%@page import="ece356.ProjectDBAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Monthly Procedure Search per Doctor</title>
    </head>
    <body>
        <h1>Monthly Procedure Search per Doctor</h1>
        
        <form method="post" action="FinanceQueries?qnum=2">
            <p>       
               Select Month:
                <select name="month">
                    <%
                    String [] month = {"01", "02", "03", "04", "05", "06", 
                        "07", "08", "09", "10", "11", "12"};
                    for (int i = 0; i < month.length; i++)
                    {
                    %>
                        <option value="<%out.print(month[i]);%>"> <%out.print(month[i]);%> </option>
                    <%
                    }
                    %>
                </select><br/>
               Select Year:
                <select name="year">
                     <%
                    int [] yearLimits = ProjectDBAO.getYears();
                    int [] year = new int[yearLimits[1] - yearLimits[0] + 1];
                    for (int i = 0; i < year.length; i++) {
                        year[i] = yearLimits[0] + i;
                    }

                    for (int i = 0; i < year.length; i++)
                    {
                    %>
                            <option value="<%out.print(year[i]);%>"> <%out.print(year[i]);%> </option>
                    <%
                    }
                    %>
                </select>
            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>
