/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import Business.Appointment;
import Business.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(urlPatterns = {"/PatientServlet"})
public class PatientServlet extends HttpServlet {

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
        
        System.out.println("Patient Servlet has been executed.");
        
        HttpSession ses1;
        RequestDispatcher rd;
        ses1 = request.getSession();
        Patient p1 = (Patient)ses1.getAttribute("p1");
         
        String function =  request.getParameter("patientFunction");
        //strings for patient info
        String id,fName,lName,addr,pass,email,ins; 
                id = p1.getPatientID();
                fName = request.getParameter("fname");
                lName = request.getParameter("lname");
                addr = request.getParameter("addr");
                pass = request.getParameter("pass");
                email = request.getParameter("eMail");
                ins = request.getParameter("insur");
        //strings for apointment info
        String apptDate, dentist, procedure;
                apptDate = request.getParameter("apptMonth")+" "+request.getParameter("apptDay")+", "+request.getParameter("apptYear")+", "+request.getParameter("apptTime");
                dentist = request.getParameter("dentistSel");
                procedure= request.getParameter("procedureSel");
        switch(function){
            case "changePatientInfo":
                
                p1.setPatientID(id);
                p1.setFirstName(fName);
                p1.setLastName(lName);
                p1.setAddress(addr);
                p1.setPassword(pass);
                p1.setEMail(email);
                p1.setInsurance(ins);
                p1.updateDB();
                ses1.setAttribute("p1", p1);
                System.out.println("patient updated in Session");                
                rd = request.getRequestDispatcher("/patient.jsp");
                rd.forward(request, response); 
                
                break;
                
            case "apptInfo":
                //System.out.println(id+" "+apptDate+" "+dentist+" "+procedure);
                Appointment a1 = new Appointment();
                boolean apptExists;
            {
                try {
                    a1.getAppointmentList();
                    for(int x=0; x<a1.alist.count;x++ ){
                        if (a1.alist.apptArr[x].getPatientID() != id){
                        apptExists = false;
                    }
                        else if(a1.alist.apptArr[x].getPatientID() == id){
                        apptExists = true; 
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                if(apptExists = true){
                a1.setAppointmentTime(apptDate);
                a1.setPatientID(id);
                a1.setDentistID(dentist);
                a1.setProcedureCode(procedure);
                a1.display();
                a1.updateDB();
                }
                if(apptExists = false){
                a1.display();
                a1.insertDB(apptDate,dentist,id,procedure);
                }
                
                
                
                
        
                rd = request.getRequestDispatcher("/patient.jsp");
                rd.forward(request, response);               
                break;

                            
                
            default:
                rd = request.getRequestDispatcher("/error.html");
                rd.forward(request, response);               
                break;
        }
        System.out.println("Patient Servlet has ended.");
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
