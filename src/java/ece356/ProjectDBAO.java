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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chrislalansingh
 */
public class ProjectDBAO {

    public static final String url = "jdbc:mysql://eceweb.uwaterloo.ca:3306/";
    public static final String nid = "cmlalans";

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, "user_cmlalans", "user_cmlalans");
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

    public static ArrayList<String> setUser(String username, String password) throws SQLException, ClassNotFoundException {
        String user = username;
        String pwd = password;
        ArrayList<String> userdata = new ArrayList<String>();
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select userID, password, type from Users where username = \'" + user + "\'");
        resultSet.next();
        userdata.add(resultSet.getString("type"));
        userdata.add(resultSet.getString("userID"));
        int asciiSum = 0;
        for (int i = 0; i < pwd.length(); i++) {
            char c = pwd.charAt(i);
            asciiSum += (int) c;
        }
        if (asciiSum == resultSet.getInt(password)) {
            return userdata;
        }
        return null;
    }

    //returns array with max and min years
    public static int[] getYears()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        int[] ret = new int[2];
        Date year;
        String[] dateString;

        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select min(procedureDate) as min from Procedures");
            resultSet.next();
            dateString = resultSet.getDate("min").toString().split("-");
            ret[0] = Integer.parseInt(dateString[0]);
            resultSet = stmt.executeQuery("select max(procedureDate) as max from Procedures");
            resultSet.next();
            dateString = resultSet.getDate("max").toString().split("-");
            ret[1] = Integer.parseInt(dateString[0]);

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

    //returns array with max and min years
    public static ArrayList<String> getMonthlyProcedures(String month, String year)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;

        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT operation, count(operation) as count"
                    + " FROM Procedures WHERE procedureDate >= '" + year + "-" + month + "-" + "01'"
                    + " AND procedureDate <= '" + year + "-" + month + "-" + "31'"
                    + " group by operation order by count desc");
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

    //returns number of procedures performed in given month by what doctor
    public static ArrayList<String> getMonthlyProceduresPerDoctor(String month, String year)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;

        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select lastName, operation, count(operation) as count"
                    + " from (Doctors as D natural join Administered), Procedures where treatmentID = procedureID"
                    + " and procedureDate >= '" + year + "-" + month + "-" + "01'"
                    + " and procedureDate <= '" + year + "-" + month + "-" + "31'"
                    + " group by lastName,operation"
                    + " order by (select count(operation) from (Doctors as d natural join Administered), Procedures"
                    + " where treatmentID = procedureID"
                    + " and procedureDate >= '" + year + "-" + month + "-" + "01'"
                    + " and procedureDate <= '" + year + "-" + month + "-" + "31'"
                    + "and d.doctorID = D.doctorID) desc");
            ret = new ArrayList<String>();
            while (resultSet.next()) {
                String p = resultSet.getString("lastName") + "," + resultSet.getString("operation")
                        + "," + resultSet.getInt("count");
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

    //returns number of all procedures performed (all time)
    public static ArrayList<String> getProcedureCount()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;

        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select operation, count(operation) "
                    + "as count from Procedures group by operation order by count desc;");
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

    //returns list of doctors
    public static ArrayList<String> getDoctors()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;

        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select doctorID, firstName, lastName from Doctors");
            ret = new ArrayList<String>();

            while (resultSet.next()) {
                String p = resultSet.getString("doctorID") + "," + resultSet.getString("firstName")
                        + "," + resultSet.getString("lastName");
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
    //currently returns list of all patients; need to edit so only returns list of patients linked to doctor corresponding to staff member

    public static ArrayList<String> getPatients()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;

        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select patientID, firstName, lastName from Patients");
            ret = new ArrayList<String>();

            while (resultSet.next()) {
                String p = resultSet.getString("patientID") + "," + resultSet.getString("firstName")
                        + "," + resultSet.getString("lastName");
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
    
    public static int insertPatient(String firstName, String lastName, String health_card, String SIN,
            String address, String phone, String doctorID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;
        int num = 0;
        try {
            con = getConnection();
            stmt = con.createStatement();
            num = stmt.executeUpdate("INSERT INTO Patients VALUES (DEFAULT, "+health_card+", '"+firstName+"', '"+lastName+"', "+SIN+", '"+address+"', '"+phone+"');");
            
            if (!doctorID.equals("0")) {
                ResultSet resultSet = stmt.executeQuery(
                        "select patientID from Patients where health_card="+health_card+" AND firstName='"+firstName+"' AND lastName='"+lastName+"' AND SIN="+SIN+" AND address='"+address+"' AND phone='"+phone+"';");
                resultSet.next();
                num += stmt.executeUpdate("INSERT INTO DoctorAssignment VALUES ("+doctorID+", "+resultSet.getString("patientID")+");");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return num;
    }
    
    public static ArrayList<String> getPatient(String patientID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = new ArrayList<String>();
        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from Patients where patientID="+patientID);
            resultSet.next();
            ret.add(resultSet.getString("firstName"));
            ret.add(resultSet.getString("lastName"));
            ret.add(resultSet.getString("health_card"));
            ret.add(resultSet.getString("SIN"));
            ret.add(resultSet.getString("address"));
            ret.add(resultSet.getString("phone"));
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
    
    public static void updatePatient(String patientID, String firstName, String lastName, String health_card, String SIN,
            String address, String phone) throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;
        int num = 0;
        System.out.println(patientID);
        try {
            con = getConnection();
            stmt = con.createStatement();
            if (!firstName.equals("")) {
                num = stmt.executeUpdate("UPDATE Patients SET firstName='"+firstName+"' WHERE patientID="+patientID);
            } if (!lastName.equals("")) {
                num = stmt.executeUpdate("UPDATE Patients SET lastName='"+lastName+"' WHERE patientID="+patientID);
            } if (!health_card.equals("")) {
                num = stmt.executeUpdate("UPDATE Patients SET health_card="+health_card+" WHERE patientID="+patientID);
            } if (!SIN.equals("")) {
                num = stmt.executeUpdate("UPDATE Patients SET SIN="+SIN+" WHERE patientID="+patientID);
            } if (!address.equals("")) {
                num = stmt.executeUpdate("UPDATE Patients SET address='"+address+"' WHERE patientID="+patientID);
            } if (!phone.equals("")) {
                num = stmt.executeUpdate("UPDATE Patients SET phone='"+phone+"' WHERE patientID="+patientID);
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public static ArrayList<String> getStaffPatients(String userID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;

        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select staffID from Staff where userID="+userID);
            resultSet.next();
            String staffID = resultSet.getString("staffID");
            resultSet = stmt.executeQuery("select patientID, firstName, lastName from Patients where patientID in (select distinct(patientID) from StaffAssignment natural join Doctors natural join DoctorAssignment where staffID="+staffID+")");
            ret = new ArrayList<String>();

            while (resultSet.next()) {
                String p = resultSet.getString("patientID") + "," + resultSet.getString("firstName")
                        + "," + resultSet.getString("lastName");
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
    
    public static void referral(String patientID, String doctorID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;
        int num;
        try {
            con = getConnection();
            stmt = con.createStatement();
            num = stmt.executeUpdate("INSERT INTO DoctorAssignment VALUES ("+doctorID+", "+patientID+");");
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
