/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brandon
 */
@WebServlet(name = "StaffQueries", urlPatterns = {"/StaffQueries"})
public class StaffQueries extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String strQueryNum = request.getParameter("qnum");
        int intQueryNum = Integer.parseInt(strQueryNum);
        String url = "";
        ArrayList<String> ret;

        try {
            //add patient
            if (intQueryNum == 1) {
                url = "/createPatientSuccess.jsp";
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String health_card = request.getParameter("health_card");
                String SIN = request.getParameter("SIN");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                String doctorID = request.getParameter("doctorID");
                int num = 0;
                if ((!firstName.equals("")) && (!lastName.equals("")) && (!health_card.equals("")) && (!SIN.equals("")) && 
                        (!address.equals("")) && (!phone.equals(""))) {
                    num = ProjectDBAO.insertPatient(firstName, lastName, health_card, SIN, address, phone, doctorID);
                } else {
                    url = "/createPatientFailure.jsp";
                }
                request.setAttribute("num", num);
            }
            //update patient
            else if (intQueryNum == 2) {
                String patientID = request.getParameter("patientID");
                ArrayList<String> al = ProjectDBAO.getPatient(patientID);
                request.setAttribute("firstName", al.get(0));
                request.setAttribute("lastName", al.get(1));
                request.setAttribute("health_card", al.get(2));
                request.setAttribute("SIN", al.get(3));
                request.setAttribute("address", al.get(4));
                request.setAttribute("phone", al.get(5));
                request.setAttribute("patientID", patientID);
                url = "/updatePatientForm.jsp";
            }
            //view patient
            else if (intQueryNum == 3) {
                String patientID = request.getParameter("patientID");
                ArrayList<String> al = ProjectDBAO.getPatient(patientID);
                request.setAttribute("firstName", al.get(0));
                request.setAttribute("lastName", al.get(1));
                request.setAttribute("health_card", al.get(2));
                request.setAttribute("SIN", al.get(3));
                request.setAttribute("address", al.get(4));
                request.setAttribute("phone", al.get(5));
                request.setAttribute("patientID", patientID);
                url = "/viewSelectedPatient.jsp"; 
            } else if (intQueryNum == 4) {
                url = "/updatePatientSuccess.jsp";
                String patientID = request.getParameter("patientID");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String health_card = request.getParameter("health_card");
                String SIN = request.getParameter("SIN");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                ProjectDBAO.updatePatient(patientID, firstName, lastName, health_card, SIN, address, phone);
            } else if (intQueryNum == 5) {
                url = "/patientReferralSuccess.jsp";
                String patientID = request.getParameter("patientID");
                String doctorID = request.getParameter("doctorID");
                ProjectDBAO.referral(patientID, doctorID);
            } else if (intQueryNum == 6) {
                String patientID = request.getParameter("patientID");
                ArrayList<String> al = ProjectDBAO.getPatientVisits(patientID);
                request.setAttribute("visits", al);
                request.setAttribute("patientID", patientID);
                url = "/viewSelectedVisits.jsp";
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
