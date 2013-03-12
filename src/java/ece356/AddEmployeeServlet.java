/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wojciech Golab
 */
public class AddEmployeeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String empName = request.getParameter("empName");

        String url;
        try {
            if (empName == null) {
                ArrayList ret = Lab3DBAO.getDepartments();
                request.setAttribute("departmentList", ret);
                url = "/addEmployee.jsp";
            } else {
                addEmployeeHelper(request, response);
                url = "/success.jsp";
            }
        } catch (Exception e) {
            request.setAttribute("exception", e);
            url = "/fancyError.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    protected void addEmployeeHelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String strEmpID = request.getParameter("empID");
        int empID = -1;
        if (!strEmpID.equals("")) {
            empID = Integer.parseInt(strEmpID);
            if (empID <= 0) {
                throw new RuntimeException("Employee ID out of range");
            }
        }
        String empName = request.getParameter("empName");
        if (empName.equals("")) {
            throw new RuntimeException("Employee name cannot be empty");
        }
        String strDeptID = request.getParameter("deptID");
        int deptID = -1;
        if (!strDeptID.equals("")) {
            deptID = Integer.parseInt(strDeptID);
            if (deptID <= 0) {
                throw new RuntimeException("Department ID out of range");
            }
        }
        String job = request.getParameter("job");
        if (job.equals("")) {
            throw new RuntimeException("Job cannot be empty");
        }
        String strSalary = request.getParameter("salary");
        int salary = -1;
        if (!strSalary.equals("")) {
            salary = Integer.parseInt(strSalary);
            if (salary <= 0) {
                throw new RuntimeException("Salary out of range");
            }
        }

        Employee em = new Employee(empID, empName, job, deptID, salary);
        Lab3DBAO.addEmployee(em);
        ArrayList ret = Lab3DBAO.getEmployees();
        request.setAttribute("employeeList", ret);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
