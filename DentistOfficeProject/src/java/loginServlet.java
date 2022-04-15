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
 *Created on : Apr 13, 2022
 *  Author     : Justin Rismiller    
 */
@WebServlet(urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

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
        
        System.out.println("Login Servlet has been executed.");
        
       
        String loginType = request.getParameter("loginType");
        String id = request.getParameter("loginID");
        RequestDispatcher rd;
        String pass = request.getParameter("password");
        System.out.println("Login Type = " + loginType);
        
        switch (loginType) {
            case "Dentist":
                Dentist d1 = new Dentist();
                try {
                    d1.selectDB(id);   //does the DB lookup to find Customer   
                    d1.display();
                    if (d1.getPassword().equals(pass)) {
                        HttpSession ses1;
                        ses1 = request.getSession();
                        ses1.setAttribute("d1", d1);
                        System.out.println("Dentist added to Session");
                        rd = request.getRequestDispatcher("/dentist.jsp"); //Need to make file for display
                        rd.forward(request, response);
                    } else {
                        rd = request.getRequestDispatcher("/error.html");
                        rd.forward(request, response);
                    }   
                } catch (IOException | ServletException ec) {
                    System.out.println(ec);
                    RequestDispatcher ecrd = request.getRequestDispatcher("/error.html");
                    ecrd.forward(request, response);
                }
                    break;
                
            case "Patient":
                Patient p1= new Patient();
                try {
                    p1.selectDB(id);   //does the DB lookup to find Customer   
                    p1.display();
                    if (p1.getPassword().equals(pass)) {
                        HttpSession ses1;
                        ses1 = request.getSession();
                        ses1.setAttribute("p1", p1);                        
                        System.out.println("patient added to Session");
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        rd = request.getRequestDispatcher("/patient.jsp"); 
                        rd.forward(request, response);
                    } else {
                        rd = request.getRequestDispatcher("/error.html");
                        rd.forward(request, response);
                    }   
                } catch (IOException | ServletException ec) {
                    System.out.println(ec);
                    rd = request.getRequestDispatcher("/error.html");
                    rd.forward(request, response);
                }
                    break;
            
            default:
                rd = request.getRequestDispatcher("/error.html");
                rd.forward(request, response);
                break;
        }
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
