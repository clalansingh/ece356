<%-- 
    Document   : index
    Created on : 11-Jan-2013, 9:14:06 AM
    Author     : Wojciech Golab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospital Management Information System</title>
    </head>
    <body>
        <h1>Hospital Management Information System</h1>
        <form action= "AuthServlet" method = "post">

            <tr>
                <td>Enter your name :</td>
                <td><input type = "text" name = "user"></td>
            </tr><br>
            <tr>
                <td>Enter your password :</td>
                <td><input type = "password" name = "pass"></td>
            </tr><br>
            <tr>
                <td><input type = "submit" name = "submit" /></td>
            </tr>

        </form>
    </body>
</html>
