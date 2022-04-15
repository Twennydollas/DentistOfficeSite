/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import java.sql.*;
/**
 *Created on : Apr 13, 2022
 *  Author     : Justin Rismiller    
 */
public class Patient extends Person{
     // ======================  Properties  =============================
    String patientID, address,  insCo;
    public PatientList plist = new PatientList();
     // ======================== Constructors ============================        
    public Patient(){
        super();
        this.patientID="";
        this.address="";        
        this.insCo="";
    }
    public Patient(String patientID,String password,String fName, String lName,String address,String email,String insCo){
        super(fName,lName,password,email);
        this.patientID=patientID;
        this.address=address;
        this.insCo=insCo;
    }


     // ==================================  Behaviors ===============================
    public void setPatientID(String patientID) {this.patientID = patientID;}
    public String getPatientID() {return patientID;}
    
    public void setAddress(String address) {this.address = address;}
    public String getAddress() {return address;}
    
    public void setInsurance(String insCo) {this.insCo = insCo;}
    public String getInsurance() {return insCo;}
    
    @Override
    public void display() {
        System.out.println("Patient ID: " + patientID);
        super.display();
        System.out.println("Address: " + address);
        System.out.println("Insurance: " + insCo);        
    }
    
     private String databaseFile(){
        //CHANGE THE PATH BELOW TO THE CORRECT LOCATION ON YOUR COMPUTER
        String filePath = "D:/school/spring 2022/java/DentistOfficeMDB(1).mdb"; //CHANGE THE FILE PATH HERE
        //CHANGE THE PATH ABOVE TO THE CORRECT LOCATION ON YOUR COMPUTER
        return filePath;
    }
        
    /************************************************************************
    * selectDB() gets the patient data from the Database 
     * @param patientID
    *************************************************************************/
    public void selectDB(String patientID){
        this.patientID = patientID;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile());
            
            Statement stmt = con.createStatement();
            String sql = "Select * from Patients where patId='"+getPatientID()+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            setFirstName(rs.getString(1));
            setLastName(rs.getString(2));
            setAddress(rs.getString(3));
            setEMail(rs.getString(4));            
            setInsurance(rs.getString(5));
            setPatientID(rs.getString(6));            
            setPassword(rs.getString(7));
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e);            
        }
        
    }//end selectDB()
    
    /************************************************************************
    * insertDB() adds a new patient to the Database
     * @param patientID
     * @param password
     * @param firstName
     * @param lastName
     * @param address
     * @param phone
     * @param email
     * @param insurance
    *************************************************************************/
    public void insertDB(String patientID, String password, String firstName, String lastName, String address, String phone, String email, String insurance){
        this.patientID = patientID;
        this.password = password;
        this.fName=firstName;
        this.lName = lastName;       
        this.address = address;
        this.email=email;
        this.insCo = insurance;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile())) {
                Statement stmt = con.createStatement();
                String sql = "Insert into Patients values('"+getPatientID()+"',"+
                        "'"+getPassword()+"',"+
                        "'"+getFirstName()+"',"+
                        "'"+getLastName()+"',"+
                        "'"+getAddress()+"',"+
                        "'"+getEMail()+"',"+
                        "'"+getInsurance()+"')";
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
    * updateDB() updates the data and information of an existing patient in the Database 
    ***************************************************************************************/
    public void updateDB(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile())) {
                Statement stmt = con.createStatement();
                String sql = "Update Patients Set passwd = '"+getPassword() + "',"+
                        " firstName = '"+getFirstName()+"',"+
                        " lastName = '"+getLastName() +"',"+
                        " addr ='"+getAddress()+"',"+
                        " email = '"+getEMail() +"',"+
                        " insCo = '"+getInsurance() +"'"+
                        " WHERE patId='"+getPatientID()+"'";
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
    * deleteDB() deletes an existing patient from the Database 
    *************************************************************************/
    public void deleteDB(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile())) {
                Statement stmt = con.createStatement();
                String sql = "Delete from Patients Where patId='"+getPatientID()+"'";
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
    * getPatientList() gets list of all existing patients from the Database 
    *************************************************************************/
    public void getPatientList() throws SQLException{
        try {
            //System.out.println("load driver ...");
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try ( //System.out.println("establish connection...");
                    Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile())) {
                Statement stmt = con.createStatement();
                String sql;
                
                sql = "Select patId from Patients";
                ResultSet rs = stmt.executeQuery(sql);
                
                
                String pat;
                Patient p1;
                while (rs.next()) {
                    pat = rs.getString(1);
                    p1 = new Patient();
                    p1.selectDB(pat);
                    plist.addPatient(p1);
                }                
            }
        } catch(ClassNotFoundException | SQLException e) {
            System.out.println("error" + e);
    }
    }
    
}
