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
 * @author chrislalansingh
 */
public class DeleteEmployeeServlet extends HttpServlet {

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
        String empID = request.getParameter("empID");

        String url;
        try {
            if (empID == null) {
                ArrayList ret = Lab3DBAO.getEmployees();
                request.setAttribute("employeeList", ret);
                url = "/deleteEmployee.jsp";
            } else {
                deleteEmployeeHelper(request, response);
                url = "/success.jsp";
            }
        } catch (Exception e) {
            request.setAttribute("exception", e);
            url = "/fancyError.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    protected void deleteEmployeeHelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        Lab3DBAO.deleteAssignment(Integer.parseInt(request.getParameter("empID")));
        Lab3DBAO.deleteEmployee(Integer.parseInt(request.getParameter("empID")));
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
