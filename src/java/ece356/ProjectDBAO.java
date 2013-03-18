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
        user_type = 3;
        return user_type;
    }
    
    //returns array with max and min years
     public static int [] getYears()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        int [] ret = new int[2];
        Date year;
        String [] dateString;

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
     
    //returns number of procedures performed in given month by what doctor
     public static ArrayList<String> getMonthlyProceduresPerDoctor(String month, String year)
           throws ClassNotFoundException, SQLException {
       Connection con = null;
       Statement stmt = null;
       ArrayList<String> ret = null;

       try {
           con = getConnection();
           stmt = con.createStatement();
           ResultSet resultSet = stmt.executeQuery("select lastName, operation, count(operation) as count" + 
                   " from (Doctors as D natural join Administered), Procedures where treatmentID = procedureID" +
                   " and procedureDate >= '" + year + "-" + month + "-" + "01'" +
                   " and procedureDate <= '" + year + "-" + month + "-" + "31'" +
                   " group by lastName,operation" +
                   " order by (select count(operation) from (Doctors as d natural join Administered), Procedures" +
                   " where treatmentID = procedureID" +
                   " and procedureDate >= '" + year + "-" + month + "-" + "01'" +
                   " and procedureDate <= '" + year + "-" + month + "-" + "31'" +
                   "and d.doctorID = D.doctorID) desc");
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

    //returns number of all procedures performed (all time)
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
             String p = resultSet.getString("doctorID") + "," + resultSet.getString("firstName") +
                     "," + resultSet.getString("lastName");
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
             String p = resultSet.getString("patientID") + "," + resultSet.getString("firstName") +
                     "," + resultSet.getString("lastName");
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
     
   public static void createVisitationRecord(int patientID, String date, int length, String diagnosis, String comments)
            throws ClassNotFoundException, SQLException {
        {
            Connection con = null;
            PreparedStatement pstmt = null;
            ArrayList ret = null;
            try {
                con = getConnection();
                pstmt = con.prepareStatement("INSERT INTO Visits VALUES(?, ?, ?, ?, ?)");
                pstmt.setInt(1, patientID);
                pstmt.setString(2, date);
                pstmt.setInt(3, length);
                pstmt.setString(4, diagnosis);
                pstmt.setString(5, comments);
                pstmt.executeUpdate();
            } finally {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        }
    } 
   
       public static ArrayList<String> queryVisitationRecords(int patientID, String date, int length, String diagnosis, String comments,
               String firstName, String lastName, String operation, int referralID, String prescription, int searchType)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList<String> ret;

        try {
            con = getConnection();
            String query = "";
            if(searchType == 1) {                
                /* Build search based on operation */
                query += "select * from Visits v, Patients p, VisitTreatment vt, Procedures pc " +
                    "where v.patientID = p.patientID and p.patientID = vt.patientID and pc.procedureID = vt.treatmentID";  
            } else if (searchType == 2) {
                /* Build search based on referral */
                query += "select * from Visits v, Patients p, VisitTreatment vt, Referrals r " +
                    "where v.patientID = p.patientID and p.patientID = vt.patientID and r.referralID = vt.treatmentID"; 
            } else {
                /* Build search based on prescription */
                query += "select * from Visits v, Patients p, VisitTreatment vt, Prescriptions pr " +
                    "where v.patientID = p.patientID and p.patientID = vt.patientID and pr.prescriptionID = vt.treatmentID";  
            }
            
            if (patientID != -1) {
                query += " AND v.patientID = ?";
            }
            if (date.length() != 0) {
                query += " AND visitDate = ?";
            }
            if (length != -1) {
                query += " AND length = ?";
            }
            if (diagnosis.length() != 0) {
                query += " AND diagnosis LIKE ?";
            }
            if (comments.length() != 0) {
                query += " AND comments LIKE ?";
            }
            if (firstName.length() != 0) {
                query += " AND firstName = ?";
            }
            if (lastName.length() != 0) {
                query += " AND lastName = ?";
            }
            if (operation.length() != 0) {
                query += " AND operation LIKE ?";
            }
            if (referralID != -1) {
                query += " AND referralID LIKE ?";
            }
            if (prescription.length() != 0) {
                query += " AND drug LIKE ?";
            }
            
            pstmt = con.prepareStatement(query);

            int num = 0;
            if (patientID != -1) {
                pstmt.setInt(++num, patientID);
            }
            if (date.length() != 0) {
                pstmt.setString(++num, date);
            }
            if (length != -1) {
                pstmt.setInt(++num, length);
            }
            if (diagnosis.length() != 0) {
                pstmt.setString(++num, "%" + diagnosis + "%");
            }
            if (comments.length() != 0) {
                pstmt.setString(++num, "%" + comments + "%");
            }
            if (firstName.length() != 0) {
                pstmt.setString(++num, firstName);
            }
            if (lastName.length() != 0) {
                pstmt.setString(++num, lastName);
            }
            if (operation.length() != 0) {
                pstmt.setString(++num, "%" + operation + "%");
            }
            if (referralID != -1) {
                pstmt.setInt(++num, referralID);
            }
            if (prescription.length() != 0) {
                 pstmt.setString(++num, "%" + prescription + "%");
            }        

            ResultSet resultSet = pstmt.executeQuery();

            ret = new ArrayList<String>();
            while (resultSet.next()) {
                String p = resultSet.getString("patientID") + "#" + resultSet.getString("visitDate") +
                     "#" + resultSet.getString("length")+ "#" + resultSet.getString("diagnosis") + 
                     "#" + resultSet.getString("firstName")+ "#" + resultSet.getString("lastName") + "#"
                        + resultSet.getString("comments");
                if(searchType == 1) {
                    p += "#" + resultSet.getString("operation");
                } else if (searchType == 2) {
                    p += "#" + resultSet.getString("referralID");
                } else {
                    p += "#" + resultSet.getString("drug");
                }
            
                ret.add(p);
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
       
    public static ArrayList<String> queryForDoctorSearchSorting(int patientID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList<String> ret;

        try {
            con = getConnection();

            /* Build SQL query */
            String query = "SELECT * FROM Visits WHERE TRUE";
 
            query += " AND patientID = ?";

            query += " ORDER BY visitDate";
            pstmt = con.prepareStatement(query);

            pstmt.setInt(1, patientID);
         

            ResultSet resultSet = pstmt.executeQuery();

            ret = new ArrayList<String>();
            while (resultSet.next()) {
                String p = resultSet.getString("patientID") + "#" + resultSet.getString("visitDate") +
                     "#" + resultSet.getString("length")+ "#" + resultSet.getString("diagnosis") + 
                     "#" + resultSet.getString("comments");
                ret.add(p);
            }
            return ret;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
