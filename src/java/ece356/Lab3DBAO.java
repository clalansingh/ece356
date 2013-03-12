package ece356;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Wojciech Golab
 */
/*
public class Lab3DBAO {

    public static final String host = "localhost";
    public static final String url = "jdbc:mysql://" + host + ":3306/";
    public static final String nid = "wgolab";
    public static final String user = "user_" + nid;
    public static final String pwd = "user_balogw";

    public static void testConnection()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        try {
            con = getConnection();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    */

public class Lab3DBAO {

    //public static final String host = "localhost";
    public static final String host = "eceweb";
    public static final String url = "jdbc:mysql://" + host + ":3306/";
    public static final String nid = "cmlalans";
    public static final String user = "user_" + nid;
    public static final String pwd = "user_" + nid;

    public static void testConnection()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        try {
            con = getConnection();
        } finally {
            if (con != null) {
                con.close();
            }
        }
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

    public static ArrayList<Employee> getEmployees()
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList<Employee> ret = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Employee");
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

    public static ArrayList getDepartments()
            throws ClassNotFoundException, SQLException {
        {
            Connection con = null;
            Statement stmt = null;
            ArrayList ret = null;
            try {
                con = getConnection();
                stmt = con.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT * FROM Department");
                ret = new ArrayList<Employee>();
                while (resultSet.next()) {
                    Department d = new Department(
                            resultSet.getInt("Department.deptID"),
                            resultSet.getString("Department.deptName"),
                            resultSet.getString("Department.location"));
                    ret.add(d);
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

    public static ArrayList<Employee> queryEmployee(int empID, String empName, int deptID, String job, int salary)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList<Employee> ret;

        try {
            con = getConnection();

            /* Build SQL query */
            String query = "SELECT * FROM Employee WHERE TRUE";
            if (empID != -1) {
                query += " AND empID = ?";
            }
            if (empName.length() != 0) {
                query += " AND empName = ?";
            }
            if (deptID != -1) {
                query += " AND deptID = ?";
            }
            if (job.length() != 0) {
                query += " AND job = ?";
            }
            if (salary != -1) {
                query += " AND salary = ?";
            }

            pstmt = con.prepareStatement(query);

            int num = 0;
            if (empID != -1) {
                pstmt.setInt(++num, empID);
            }
            if (empName.length() != 0) {
                pstmt.setString(++num, empName);
            }
            if (deptID != -1) {
                pstmt.setInt(++num, deptID);
            }
            if (job.length() != 0) {
                pstmt.setString(++num, job);
            }
            if (salary != -1) {
                pstmt.setInt(++num, salary);
            }

            ResultSet resultSet;
            resultSet = pstmt.executeQuery();

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
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public static void addEmployee(Employee em)
            throws ClassNotFoundException, SQLException {
        {
            Connection con = null;
            PreparedStatement pstmt = null;
            ArrayList ret = null;
            try {
                con = getConnection();
                pstmt = con.prepareStatement("INSERT INTO Employee VALUES(?, ?, ?, ?, ?)");
                pstmt.setInt(1, em.getEmpID());
                pstmt.setString(2, em.getEmpName());
                pstmt.setString(3, em.getJob());
                pstmt.setInt(4, em.getDeptID());
                pstmt.setInt(5, em.getSalary());
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

    public static void deleteEmployee(int empID)
            throws ClassNotFoundException, SQLException {
        {
            Connection con = null;
            PreparedStatement pstmt = null;
            ArrayList ret = null;
            try {
                con = getConnection();
                pstmt = con.prepareStatement("DELETE FROM Employee WHERE empID = ?");
                pstmt.setInt(1, empID);
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
    
    public static void deleteAssignment(int empID)
            throws ClassNotFoundException, SQLException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList ret = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement("delete from Assigned where empID = ?");
            pstmt.setInt(1, empID);
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
