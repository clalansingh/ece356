/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chrislalansingh
 */
public class AuthServlet extends HttpServlet {

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
    
    public static final String url = "jdbc:mysql://eceweb.uwaterloo.ca:3306/";
    public static final String nid = "cmlalans"; //for the purpose of using hospital_cmlalans as the db
    public static String user = "";
    public static String pwd = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        user = request.getParameter("user");
        pwd = request.getParameter("pass");
        HttpSession session = request.getSession(true);
        //temporarily hardcoded user and password for testing (tired of typing ti in every time) 
//        user = "user_bdtheoba";
//        pwd = "user_bdtheoba";
        ArrayList<String> userdata = ProjectDBAO.setUser(user, pwd);
        String redirect;
        if (userdata != null) {
            String type = userdata.get(0);
            String userid = userdata.get(1);
            if (!type.equals(null)) {
                User newUser = new User();
                newUser.createUser(user, type, userid);
                session.setAttribute("user", newUser);
            }
            if (type.equals("finance")) {
                redirect = "/Finance.jsp";
            } else if (type.equals("staff")) {
                redirect = "/Staff.jsp";
            } else if (type.equals("doctor")) {
                redirect = "/Doctor.jsp";
            } else {
                redirect = "/index.jsp";
            }
        } else {
            redirect = "/index.jsp";
        }
        getServletContext().getRequestDispatcher(redirect).forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
