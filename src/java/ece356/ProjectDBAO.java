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
    public static String user = "";
    public static String pwd = "";
    public static int user_type;
    
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
        if (asciiSum == resultSet.getInt("password")) {
            return userdata;
        }
        return null;
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
   
       public static ArrayList<String> queryVisitationRecords(int doctorID, int patientID, String date, int length, String diagnosis, String comments,
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
                query += "select * from Visits v, Patients p, DoctorAssignment da, VisitTreatment vt, Procedures pc" +
                    " where da.doctorID =" + doctorID + " and da.patientID = v.patientID" + 
                    " and v.patientID = p.patientID and p.patientID = vt.patientID and pc.procedureID = vt.treatmentID";  
            } else if (searchType == 2) {
                /* Build search based on referral */
                query += "select * from Visits v, Patients p, DoctorAssignment da, VisitTreatment vt, Referrals r" +
                    " where da.doctorID =" + doctorID + " and da.patientID = v.patientID" + 
                    " and v.patientID = p.patientID and p.patientID = vt.patientID and r.referralID = vt.treatmentID"; 
            } else {
                /* Build search based on prescription */
                query += "select * from Visits v, Patients p, DoctorAssignment da, VisitTreatment vt, Prescriptions pr" +
                    " where da.doctorID =" + doctorID + " and da.patientID = v.patientID" + 
                    " and v.patientID = p.patientID and p.patientID = vt.patientID and pr.prescriptionID = vt.treatmentID";  
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
            String query = "SELECT * FROM Visits natural join Patients WHERE TRUE";
 
            query += " AND patientID = ?";

            query += " ORDER BY visitDate";
            pstmt = con.prepareStatement(query);

            pstmt.setInt(1, patientID);
         

            ResultSet resultSet = pstmt.executeQuery();

            ret = new ArrayList<String>();
            while (resultSet.next()) {
                String p = resultSet.getString("patientID") + "#" + resultSet.getString("visitDate") +
                     "#" + resultSet.getString("length")+ "#" + resultSet.getString("diagnosis") + 
                     "#" + resultSet.getString("firstName")+ "#" + resultSet.getString("lastName") + "#"
                        + resultSet.getString("comments");
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
	
	public static int insertPatient(String firstName, String lastName, String health_card, String SIN,
            String address, String phone, String doctorID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ArrayList<String> ret = null;
        int num = 0;
        try {
            con = getConnection();
            stmt = con.createStatement();
            pstmt = con.prepareStatement("INSERT INTO Patients VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, Integer.parseInt(health_card));
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setInt(4, Integer.parseInt(SIN));
            pstmt.setString(5, address);
            pstmt.setString(6, phone);
            num += pstmt.executeUpdate();
            if (!doctorID.equals("0")) {
                pstmt = con.prepareStatement("select patientID from Patients where health_card=? AND firstName=? AND lastName=? AND SIN=? AND address=? AND phone=?");
                pstmt.setInt(1, Integer.parseInt(health_card));
                pstmt.setString(2, firstName);
                pstmt.setString(3, lastName);
                pstmt.setInt(4, Integer.parseInt(SIN));
                pstmt.setString(5, address);
                pstmt.setString(6, phone);
                ResultSet resultSet = pstmt.executeQuery();
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
        PreparedStatement pstmt = null;
        ArrayList<String> ret = null;
        int num = 0;
        System.out.println(patientID);
        try {
            con = getConnection();
            stmt = con.createStatement();
            if (!firstName.equals("")) {
                pstmt = con.prepareStatement("UPDATE Patients SET firstName=? WHERE patientID=?");
                pstmt.setString(1, firstName);
                pstmt.setInt(2, Integer.parseInt(patientID));
                num = pstmt.executeUpdate();
            } if (!lastName.equals("")) {
                pstmt = con.prepareStatement("UPDATE Patients SET lastName=? WHERE patientID=?");
                pstmt.setString(1, lastName);
                pstmt.setInt(2, Integer.parseInt(patientID));
                num = pstmt.executeUpdate();
            } if (!health_card.equals("")) {
                pstmt = con.prepareStatement("UPDATE Patients SET health_card=? WHERE patientID=?");
                pstmt.setInt(1, Integer.parseInt(health_card));
                pstmt.setInt(2, Integer.parseInt(patientID));
                num = pstmt.executeUpdate();
            } if (!SIN.equals("")) {
                pstmt = con.prepareStatement("UPDATE Patients SET SIN=? WHERE patientID=?");
                pstmt.setInt(1, Integer.parseInt(SIN));
                pstmt.setInt(2, Integer.parseInt(patientID));
                num = pstmt.executeUpdate();
            } if (!address.equals("")) {
                pstmt = con.prepareStatement("UPDATE Patients SET address=? WHERE patientID=?");
                pstmt.setString(1, address);
                pstmt.setInt(2, Integer.parseInt(patientID));
                num = pstmt.executeUpdate();
            } if (!phone.equals("")) {
                pstmt = con.prepareStatement("UPDATE Patients SET phone=? WHERE patientID=?");
                pstmt.setString(1, phone);
                pstmt.setInt(2, Integer.parseInt(patientID));
                num = pstmt.executeUpdate();
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
    
    public static ArrayList<String> getDoctorPatients(String userID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;

        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select doctorID from Doctors where userID="+userID);
            resultSet.next();
            String doctorID = resultSet.getString("doctorID");
            resultSet = stmt.executeQuery("select patientID, firstName, lastName from Patients where patientID in (select distinct(patientID) from DoctorAssignment where doctorID="+doctorID+")");
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
    
    public static String getDoctorID(String userID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;

        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select doctorID from Doctors where userID="+userID);
            resultSet.next();
            String doctorID = resultSet.getString("doctorID");        

          
            return doctorID;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public static ArrayList<String> queryPrescriptionsForPatient(int patientID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret;

        try {
            ret = new ArrayList<String>();
            con = getConnection();

            /* Build SQL query */
            String query = "select drug, dosage, v.visitDate from Visits v,VisitTreatment vt, Prescriptions pr" + 
                    " where v.patientID =" + patientID + " AND v.patientID = vt.patientID and v.visitDate = vt.visitDate and vt.treatmentID = pr.prescriptionID";
 
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            ret = new ArrayList<String>();
            while (resultSet.next()) {
                String p = resultSet.getString("drug") + "#" + resultSet.getString("dosage") + "#" + resultSet.getString("visitDate");
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
    
    public static ArrayList<String> queryProceduresForPatient(int patientID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret;

        try {
            con = getConnection();
            stmt = con.createStatement();
            /* Build SQL query */
            String query = "select operation, v.visitDate from Visits v,VisitTreatment vt, Procedures pc" + 
                    " where v.patientID =" + patientID + " AND v.patientID = vt.patientID and v.visitDate = vt.visitDate and vt.treatmentID = pc.procedureID";
 
            ResultSet resultSet = stmt.executeQuery(query);

            ret = new ArrayList<String>();
            while (resultSet.next()) {
                String p = resultSet.getString("operation") + "#" + resultSet.getString("visitDate");
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
    
    public static ArrayList<String> queryReferralsForPatient(int patientID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret;

        try {
            con = getConnection();
            stmt = con.createStatement();
            /* Build SQL query */
            String query = "select referralID, v.visitDate from Visits v,VisitTreatment vt, Referrals r" + 
                    " where v.patientID =" + patientID + " AND v.patientID = vt.patientID and v.visitDate = vt.visitDate and vt.treatmentID = r.referralID";
 
            ResultSet resultSet = stmt.executeQuery(query);

            ret = new ArrayList<String>();
            while (resultSet.next()) {
                String p = resultSet.getString("referralID") + "#" + resultSet.getString("visitDate");
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
    public static ArrayList<String> getPatientVisits(String patientID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> ret = null;
        try {
            ret = new ArrayList<String>();
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from Visits where patientID="+patientID);
            while (resultSet.next()) {
                String p = resultSet.getString("visitDate") + "," + resultSet.getString("length")
                        + "," + resultSet.getString("diagnosis") + "," + resultSet.getString("comments");
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
