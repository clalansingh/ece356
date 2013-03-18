/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leon Zhang
 */
@WebServlet(name = "DoctorSearchServlet", urlPatterns = {"/DoctorSearchServlet"})
public class DoctorSearchServlet extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        String url = "/index.jsp";

        
        String strIndex = request.getParameter("index");
        int index = Integer.parseInt(strIndex);
        
        ArrayList <String> searchResults = (ArrayList<String>)session.getAttribute("searchResults");        
        String record = searchResults.get(index); 
        String splitRecords[] = record.split("#");
        String patientID = splitRecords[0];
        String date = splitRecords[1];
        int length = 0;
        for(String s: searchResults) {
            String[] sRecord = s.split("#");
            String sPatientID = sRecord[0];
            if(patientID.equals(sPatientID)) {
                length++;
            }
        }
        
        try {
            ArrayList<String> ret = ProjectDBAO.queryForDoctorSearchSorting(Integer.parseInt(patientID));
            
            session.setAttribute("searchLength",length);
            index = 0;
            for (String retRecord : ret) {
                String retSplitRecords[] = retRecord.split("#");
                String retDate = retSplitRecords[1];
                if (retDate.equals(date)) {
                    break;
                }
                index++;
            }
            url = "/doctorSearchFullView.jsp"; 
        } catch (Exception e) {
            request.setAttribute("exception", e);
            url = "/fancyError.jsp";
        }       
        
        request.setAttribute("record", record);

        request.setAttribute("index", index);
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
