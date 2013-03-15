/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import java.sql.Connection;
import java.sql.Date;
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
import java.util.Calendar;

/**
 *
 * @author chrislalansingh
 */
public class ProjectDBAO {
    
    public static final String url = "jdbc:mysql://eceweb.uwaterloo.ca:3306/";
    public static final String nid = "jcmtang"; 
    public static String user = "";
    public static String pwd = "";
    public static int user_type;
    
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
    
     public static ArrayList<String> getMonthlyProcedures(String month, String year)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;
        
        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT operation, count(operation) as count" +
                    " FROM Procedures WHERE procedureDate >= '" + year + "-" + month + "-" + "01'" +
                    " AND procedureDate <= '" + year + "-" + month + "-" + "31'" +
                    " group by operation order by count desc");
            ret = new ArrayList<String>();
            while (resultSet.next()) {
                String p = resultSet.getString("operation") + "," + resultSet.getInt("count");
                ret.add(p);
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
     
      public static ArrayList<String> getMonthlyProceduresPerDoctor(String month, String year)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;
        
        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select lastName, operation, count(operation) as count" + 
                    " from (Doctors natural join Administered), Procedures where treatmentID = procedureID" +
                    //" and procedureDate >= '" + year + "-" + month + "-" + "01'" +
                    //" and procedureDate <= '" + year + "-" + month + "-" + "31'" +
                    " group by lastName,operation");
            ret = new ArrayList<String>();
            while (resultSet.next()) {
                String p = resultSet.getString("lastName") + "," + resultSet.getString("operation") + 
                        "," + resultSet.getInt("count");
                ret.add(p);
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
     
     public static ArrayList<String> getProcedureCount()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;
        
        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select operation, count(operation) " + 
                    "as count from Procedures group by operation order by count desc;");
            ret = new ArrayList<String>();
            
            while (resultSet.next()) {
                String p = resultSet.getString("operation") + "," + resultSet.getInt("count");
                ret.add(p);
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
    
}
