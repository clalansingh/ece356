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
import java.sql.Statement;
import java.util.ArrayList;
import org.jasypt.util.password.StrongPasswordEncryptor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ece356.auth;

/**
 *
 * @author chrislalansingh
 */
public class ProjectDBAO {
    
    public static final String url = "jdbc:mysql://eceweb.uwaterloo.ca:3306/";
    public static final String nid = "jcmtang"; //for the purpose of using hospital_cmlalans as the db
    public static String user = "";
    public static String pwd = "";
    public static int user_type;
    
    public static ArrayList<Employee> query1()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<Employee> ret = null;
        if ((user != "") && (pwd != "")) {
            try {
                con = ProjectDBAO.getConnection();
                stmt = con.createStatement();
                ResultSet resultSet = stmt.executeQuery(
                        "SELECT * FROM Employee WHERE job = 'engineer' AND salary >= 10000;");
                ret = new ArrayList<Employee>();
                while (resultSet.next()) {
                    Employee e = new Employee(
                            resultSet.getInt("empID"),
                            resultSet.getString("empName"),
                            resultSet.getString("job"),
                            resultSet.getInt("deptID"),
                            resultSet.getInt("salary"));
                    ret.add(e);
                }
                return ret;
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        }
        return null;
    }
    
    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, pwd);
        Statement stmt = null;
        try {
            con.createStatement();
            stmt = con.createStatement();
            stmt.execute("USE hospital_" + nid);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return con;
    }
    
    public static int setUser(String username, String password) {
        user = username;
        pwd = password;
        user_type = 1;
        return user_type;
    }
    
}
