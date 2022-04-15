/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *Created on : Apr 13, 2022
 *  Author     : Justin Rismiller    
 */
public class Appointment {
     // ======================  Properties  =============================
    String apptTime,patID,dentID,procCD;
    public AppointmentList alist = new AppointmentList();
    // ======================== Constructors ============================   
    public Appointment(){
        this.apptTime="";
        patID="";
        dentID="";
        procCD="";
    }
    public Appointment(String appointmentTime, String patientID, String dentistID,String procedureCode){
        this.apptTime=appointmentTime;
        this.patID=patientID;
        this.dentID=dentistID;
        this.procCD=procedureCode;
    }
     // ==================================  Behaviors ===============================
    public void setAppointmentTime(String appointmentTime) {this.apptTime = appointmentTime;}
    public String getAppointmentTime() {return apptTime;}
    public void setPatientID(String patientID) {this.patID = patientID;}
    public String getPatientID() {return patID;}
    public void setDentistID(String dentistID) {this.dentID = dentistID;}
    public String getDentistID() {return dentID;}
    public void setProcedureCode(String procedureCode) {this.procCD = procedureCode;}
    public String getProcedureCode() {return procCD;}
    
    public void display() {
        System.out.println("Appointment Time: " + apptTime);        
        System.out.println("Patient ID: " + patID);
        System.out.println("Dentist ID: " + dentID);        
        System.out.println("Procedure Code: " + procCD);        
    }
    
    private String databaseFile(){
        //CHANGE THE PATH BELOW TO THE CORRECT LOCATION ON YOUR COMPUTER
        String filePath = "D:/school/spring 2022/java/DentistOfficeMDB(1).mdb"; //CHANGE THE FILE PATH HERE
        //CHANGE THE PATH ABOVE TO THE CORRECT LOCATION ON YOUR COMPUTER
        return filePath;
    }
        
    /************************************************************************
    * selectDB() gets the appointment data from the Database
     * @param patientID
     * 
    *************************************************************************/
    public void selectDB(String patientID){
        this.patID = patientID;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile());
            
            Statement stmt = con.createStatement();
            String sql = "Select * from Appointments where patId='"+getPatientID()+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            setAppointmentTime(rs.getString(1));
            setPatientID(rs.getString(3));
            setDentistID(rs.getString(4));
            setProcedureCode(rs.getString(2));
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e);            
        }
        
    }//end selectDB()
    /************************************************************************
    * insertDB() adds a new appointment to the Database
     * @param apptTime
     * @param denistID
     * @param patientID
     * @param procedureCode
    *************************************************************************/
    public void insertDB(String apptTime, String denistID, String patientID, String procedureCode){
        
        this.apptTime=apptTime;
        this.dentID = denistID;
        this.patID = patientID;
        this.procCD=procedureCode;
       
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile())) {
                Statement stmt = con.createStatement();
                String sql = "Insert into Appointments values('"+getAppointmentTime()+"',"+
                        "'"+getPatientID()+"',"+
                        "'"+getDentistID()+"',"+
                        "'"+getProcedureCode()+"')";
                System.out.println(sql);
                int n1 = stmt.executeUpdate(sql);
                if (n1==1)
                    System.out.println("INSERT Successful!!!");
                else
                    System.out.println("INSERT FAILED***********");
            }
        }
        catch(ClassNotFoundException | SQLException e1){
            System.out.println(e1);
        }
    }//end insertDB()
    
    /**************************************************************************************
    * updateDB() updates the data and information of an existing appointment in the Database 
    ***************************************************************************************/
    public void updateDB(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile())) {
                Statement stmt = con.createStatement();
                String sql = "Update Appointments Set apptDateTime='"+getAppointmentTime()+ "',"+
                        " dentId = '"+getDentistID()+"',"+
                        " procCode = '"+getProcedureCode() +"'"+
                        " Where patId='"+getPatientID()+"'";
                System.out.println(sql);
                int n = stmt.executeUpdate(sql);
                if (n==1)
                    System.out.println("UPDATE Successful!!!");
                else
                    System.out.println("UPDATE FAILED***********");
            }
        }
        catch(ClassNotFoundException | SQLException e1){
            System.out.println(e1);
        }
    }//end updateDB()
    
    /************************************************************************
    * deleteDB() deletes an existing appointment from the Database 
    *************************************************************************/
    public void deleteDB(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile())) {
                Statement stmt = con.createStatement();
                String sql = "Delete from Appointments Where patId='"+getPatientID()+"'";
                System.out.println(sql);
                int n = stmt.executeUpdate(sql);
                if (n==1)
                    System.out.println("DELETE Successful!!!");
                else
                    System.out.println("DELETE FAILED***********");
            }
        }
        catch(ClassNotFoundException | SQLException e1){
            System.out.println(e1);
        }
    }//end deleteDB()
     /************************************************************************
    * getAppointmentList() gets list of all existing appointments from the Database 
    *************************************************************************/    
    public void getAppointmentList() throws SQLException{
        try {
            //System.out.println("load driver ...");
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try ( //System.out.println("establish connection...");
                    Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile())) {
                Statement stmt = con.createStatement();
                String sql;
                
                sql = "Select patId from Appointments";
                ResultSet rs = stmt.executeQuery(sql);
                
                
                String proc;
                Appointment a1;
                while (rs.next()) {
                    proc = rs.getString(1);
                    a1 = new Appointment();
                    a1.selectDB(proc);
                    alist.addAppointment(a1);
                }                
            }
        } catch(ClassNotFoundException | SQLException e) {
            System.out.println("error" + e);
    }
    }
    public static void main(String args[]) throws SQLException{
        Appointment a1 = new Appointment();
        a1.selectDB("A900");
        a1.display();
    }
}
