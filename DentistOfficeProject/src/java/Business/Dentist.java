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
public class Dentist extends Person{
      // ======================  Properties  =============================
    String denistID, office;
    public DentistList dlist = new DentistList();
     // ======================== Constructors ============================        
    public Dentist(){
        super();
        this.denistID="";
        this.office="";
       
    }
    public Dentist(String denistID,String password,String fName, String lName,String office,String email){
        super(fName,lName,password,email);
        this.denistID=denistID;
        this.office=office;
    }
     // ==================================  Behaviors ===============================
    public void setDentistID(String denistID) {this.denistID = denistID;}
    public String getDentistID() {return denistID;}
    
    public void setOffice(String office) {this.office = office;}
    public String getOffice() {return office;}
        
    @Override
    public void display() {
        System.out.println("Dentist ID: " + denistID);
        super.display();
        System.out.println("Office: " + office);        
    }

    private String databaseFile(){
        //CHANGE THE PATH BELOW TO THE CORRECT LOCATION ON YOUR COMPUTER
        String filePath = "D:/school/spring 2022/java/DentistOfficeMDB(1).mdb"; //CHANGE THE FILE PATH HERE
        //CHANGE THE PATH ABOVE TO THE CORRECT LOCATION ON YOUR COMPUTER
        return filePath;
    }
        
    /************************************************************************
    * selectDB() gets the dentist data from the Database
     * @param denistID
    *************************************************************************/
    public void selectDB(String denistID){
        this.denistID = denistID;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile());
            
            Statement stmt = con.createStatement();
            String sql = "Select * from Dentists where id='"+getDentistID()+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            setFirstName(rs.getString(1));
            setLastName(rs.getString(2));
            setOffice(rs.getString(6));
            setEMail(rs.getString(3));           
            setDentistID(rs.getString(4));            
            setPassword(rs.getString(5));
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e);            
        }
        
    }//end selectDB()
    /************************************************************************
    * insertDB() adds a new dentist to the Database
     * @param denistID
     * @param password
     * @param firstName
     * @param lastName
     * @param office
     * @param email
    *************************************************************************/
    public void insertDB(String denistID, String password, String firstName, String lastName, String office, String email){
        this.denistID = denistID;
        this.password = password;
        this.fName=firstName;
        this.lName = lastName;       
        this.office = office;
        this.email=email;
       
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile())) {
                Statement stmt = con.createStatement();
                String sql = "Insert into Dentists values('"+getDentistID()+"',"+
                        "'"+getPassword()+"',"+
                        "'"+getFirstName()+"',"+
                        "'"+getLastName()+"',"+
                        "'"+getEMail()+"',"+
                        "'"+getOffice()+"')";
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
    * updateDB() updates the data and information of an existing dentist in the Database 
    ***************************************************************************************/
    public void updateDB(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile())) {
                Statement stmt = con.createStatement();
                String sql = "Update Dentists Set passwd = '"+getPassword() + "',"+
                        " firstName = '"+getFirstName()+"',"+
                        " lastName = '"+getLastName() +"',"+
                        " office ='"+getOffice()+"',"+
                        " email = '"+getEMail() +"'"+
                        " WHERE id='"+getDentistID()+"'";
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
    * deleteDB() deletes an existing dentist from the Database 
    *************************************************************************/
    public void deleteDB(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile())) {
                Statement stmt = con.createStatement();
                String sql = "Delete from Dentists Where id='"+getDentistID()+"'";
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
    * getDentistList() gets list of all existing dentists from the Database 
    *************************************************************************/
    public void getDentistList() throws SQLException{
        try {
            //System.out.println("load driver ...");
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try ( //System.out.println("establish connection...");
                    Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile())) {
                Statement stmt = con.createStatement();
                String sql;
                
                sql = "Select id from Dentists";
                ResultSet rs = stmt.executeQuery(sql);
                
                
                String dent;
                Dentist d1;
                while (rs.next()) {
                    dent = rs.getString(1);
                    d1 = new Dentist();
                    d1.selectDB(dent);
                    dlist.addDentist(d1);
                }                
            }
        } catch(ClassNotFoundException | SQLException e) {
            System.out.println("error" + e);
    }
    }
    
    
    public static void main(String args[]) throws SQLException{
        Dentist a1 = new Dentist();
        a1.selectDB("D201");
        a1.display();
    }
}
