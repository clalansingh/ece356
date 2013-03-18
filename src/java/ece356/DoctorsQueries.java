/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leon Zhang
 */
@WebServlet(name = "DoctorsQueries", urlPatterns = {"/DoctorsQueries"})
public class DoctorsQueries extends HttpServlet {

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
        String url = "/index.jsp";
        
        String strQueryNum = request.getParameter("qnum");
        int intQueryNum = Integer.parseInt(strQueryNum);
        
        if(intQueryNum == 1) {
            try {
                createVisitationRecordHelper(request, response);
                url = "/Doctor.jsp";
            } catch (Exception e) {
                request.setAttribute("exception", e);
                url = "/fancyError.jsp";
            }       
        } else if(intQueryNum == 2) {
            try {
                searchRecordsHelper(request, response);
                url = "/doctorSearchListView.jsp";
            } catch (Exception e) {
                request.setAttribute("exception", e);
                url = "/fancyError.jsp";
            }       
        }        

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    protected void createVisitationRecordHelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
            
        String strPatientID = request.getParameter("patientID");
        int patientID;
        if (!strPatientID.equals("")) {
            patientID = Integer.parseInt(strPatientID);
        } else {
            throw new RuntimeException("Patient ID out of range");
        }

        String date = request.getParameter("date");

        String strLength = request.getParameter("length");
        int length;
        if (!strLength.equals("")) {
            length = Integer.parseInt(strLength);
        } else {
            throw new RuntimeException("Length out of range");
        }
        String diagnosis = request.getParameter("diagnosis"); 
        String comments = request.getParameter("comments");

        ProjectDBAO.createVisitationRecord(patientID, date, length, diagnosis, comments);             
    }
    
    protected void searchRecordsHelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
            
        String strPatientID = request.getParameter("patientID");
        int patientID = -1;
        if (!strPatientID.equals("")) {
            patientID = Integer.parseInt(strPatientID);
        }
        
        String date = request.getParameter("date");

        String strLength = request.getParameter("length");
        int length = -1;
        if (!strLength.equals("")) {
            length = Integer.parseInt(strLength);
        }
        String diagnosis = request.getParameter("diagnosis"); 
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String treatmentType = request.getParameter("treatmentType");
        String operation = "";
        String prescription = "";
        int referralID = -1; 
        int searchType = 0;
        if(treatmentType.equals("prescription")) {
            searchType = 3;
            prescription += request.getParameter("treatment");
        } else if (treatmentType.equals("operation")) {
            searchType = 1;
            operation += request.getParameter("treatment");
        } else {
            searchType = 1;
            String strReferralID = request.getParameter("treatment");
            referralID = Integer.parseInt(strReferralID);
        }
        
        String comments = request.getParameter("comments");
        ArrayList ret = ProjectDBAO.queryVisitationRecords(patientID, date, length, diagnosis, comments, firstName, lastName, operation, referralID, prescription, searchType); 
        request.setAttribute("ret", ret);
            
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
