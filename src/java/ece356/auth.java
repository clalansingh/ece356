/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 *
 * @author chrislalansingh
 */
public class auth extends HttpServlet{
    
    protected boolean verifyUser(String user, String pass) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

        Connection conn = null;
        String userName = "****";
        String password = "****";
        String url = "jdbc:mysql://eceweb.uwaterloo.ca:3306/";
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");

            PreparedStatement stmt = null;

            stmt = conn.prepareStatement("SELECT * FROM DB WHERE USER = ?");
            stmt.setString(1, user);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                return false;
            }

            if (passwordEncryptor.checkPassword(pass, rs.getString("Password"))) {
                return true;
            }
            conn.close();
        } catch (Exception e) {
        }
        getServletContext().getRequestDispatcher("Finance.jsp");
        return false;
    }
    
}
