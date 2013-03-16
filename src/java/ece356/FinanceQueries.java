/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brandon
 */
@WebServlet(name = "FinanceQueries", urlPatterns = {"/FinanceQueries"})
public class FinanceQueries extends HttpServlet {

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
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        String strQueryNum = request.getParameter("qnum");
        int intQueryNum = Integer.parseInt(strQueryNum);
        String url;
        ArrayList<String> ret;
                
        try {
            //count total procedures per month
            if (intQueryNum == 1) {
                String month = request.getParameter("month");
                String year = request.getParameter("year");
                ret = ProjectDBAO.getMonthlyProcedures(month, year);
                request.setAttribute("month", month);
                request.setAttribute("year", year);
                request.setAttribute("ret", ret);
                url = "/monthlyProcedures.jsp";
            }
            //count procedures by each doctor per month
            else if (intQueryNum == 2) {
                String month = request.getParameter("month");
                String year = request.getParameter("year");
                ret = ProjectDBAO.getMonthlyProceduresPerDoctor(month, year);
                request.setAttribute("month", month);
                request.setAttribute("year", year);
                request.setAttribute("ret", ret);
                url = "/monthlyProcedures2.jsp";
            }
            //count all procedures
            else if (intQueryNum == 3) {
                ret = ProjectDBAO.getProcedureCount();
                request.setAttribute("ret", ret);
                url = "/allProcedures.jsp";
            }
            else {
                throw new RuntimeException("Invalid query number: " + intQueryNum);
            }
        } catch (Exception e) {
            request.setAttribute("exception", e);
            url = "/fancyError.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
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
