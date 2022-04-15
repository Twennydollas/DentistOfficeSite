/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import Business.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jdris
 */
@WebServlet(urlPatterns = {"/DentistServlet"})
public class DentistServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         System.out.println("Dentist Servlet has been executed.");
        
        HttpSession ses1;
        RequestDispatcher rd;
        ses1 = request.getSession();
        Dentist d1 = (Dentist)ses1.getAttribute("d1");
        
        String id,fName,lName,pass,email,off; 
                id = d1.getDentistID();
                fName = request.getParameter("fname");
                lName = request.getParameter("lname");
                pass = request.getParameter("pass");
                email = request.getParameter("eMail");
                off = request.getParameter("office");
                
                d1.setDentistID(id);
                d1.setFirstName(fName);
                d1.setLastName(lName);
                d1.setOffice(off);
                d1.setPassword(pass);
                d1.setEMail(email);                
                d1.updateDB();
                ses1.setAttribute("d1", d1);
                System.out.println("Dentist updated in  Session");
                          
                rd = request.getRequestDispatcher("/dentist.jsp");
                    rd.forward(request, response);      
                    System.out.println("Dentist Servlet has ended.");
                
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
