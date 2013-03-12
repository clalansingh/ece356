<%-- 
    Document   : queryEmployee
    Created on : 14-Jan-2013, 2:50:09 PM
    Author     : talguind
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Query Employees</title>
    </head>
    <body>
        <h1>Query Employees</h1>
        
        <form method="post" action="QueryServlet?qnum=3">
            Enter selection criteria:
            <p>
            Employee ID: <input type="text" name="empID" size="20" autofocus><br/>
            Employee name: <input type="text" name="empName" size="20" autofocus><br/> 
            Job:<input type="text" name="job" size="20" autofocus><br/>
            Dept ID: <input type="text" name="deptID" size="20" autofocus><br/>
            Salary: <input type="text" name="salary" size="20" autofocus><br/>


            <p> <input type="submit">
        </form>

        
        
        
    </body>
</html>
