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
public class Procedure {
     // ======================  Properties  =============================
    String procName,desc,cost,procCD;
    public ProcedureList plist = new ProcedureList();
    // ======================== Constructors ============================   
    public Procedure(){
        this.procCD="";
        this.procName="";
        this.desc="";
        this.cost="";
        
    }
    public Procedure(String procedureCode, String procedureName, String description,String cost){
        this.procCD=procedureCode;
        this.procName=procedureName;
        this.desc=description;
        this.cost=cost;
    }
     // ==================================  Behaviors ===============================
    public void setProcedureCode(String procedureCode) {this.procCD = procedureCode;}
    public String getProcedureCode() {return procCD;}
    public void setProcedureName(String procedureName) {this.procName = procedureName;}
    public String getProcedureName() {return procName;}
    public void setDescription(String description) {this.desc = description;}
    public String getDescription() {return desc;}
    public void setCost(String cost) {this.cost = cost;}
    public String getCost() {return cost;}
    
    
    public void display() {
        System.out.println("Procedure Code: " + procCD);
        System.out.println("Procedure Name: " + procName);        
        System.out.println("Description: " + desc);
        System.out.println("Cost: " + cost);        
    }
    
    private String databaseFile(){
        //CHANGE THE PATH BELOW TO THE CORRECT LOCATION ON YOUR COMPUTER
        String filePath = "D:/school/spring 2022/java/DentistOfficeMDB(1).mdb"; //CHANGE THE FILE PATH HERE
        //CHANGE THE PATH ABOVE TO THE CORRECT LOCATION ON YOUR COMPUTER
        return filePath;
    }
        
    
    /************************************************************************
    * selectDB() gets the procedure data from the Database
     * @param procedureCode
    *************************************************************************/
    public void selectDB(String procedureCode){
        this.procCD = procedureCode;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile());
            
            Statement stmt = con.createStatement();
            String sql = "Select * from Procedures where procCode='"+getProcedureCode()+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            setProcedureCode(rs.getString(1));
            setProcedureName(rs.getString(2));
            setDescription(rs.getString(3));
            setCost(rs.getString(4));
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e);            
        }
        
    }//end selectDB()
    /************************************************************************
    * getProcedureList() gets list of all existing procedures from the Database 
    *************************************************************************/
    public void getProcedureList() throws SQLException{
        try {
            //System.out.println("load driver ...");
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try ( //System.out.println("establish connection...");
                    Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+databaseFile())) {
                Statement stmt = con.createStatement();
                String sql;
                
                sql = "Select procCode from Procedures";
                ResultSet rs = stmt.executeQuery(sql);
                
                
                String proc;
                Procedure p1;
                while (rs.next()) {
                    proc = rs.getString(1);
                    p1 = new Procedure();
                    p1.selectDB(proc);
                    plist.addProcedure(p1);
                }                
            }
        } catch(ClassNotFoundException | SQLException e) {
            System.out.println("error" + e);
    }
    }
    public static void main(String args[]) throws SQLException{
        Procedure a1 = new Procedure();
        a1.getProcedureList();        
        a1.plist.displayList();       
        
    }
    
}
