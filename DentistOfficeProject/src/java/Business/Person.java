/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

/**
 *Created on : Apr 13, 2022
 *  Author     : Justin Rismiller    
 */
public class Person {
    
     // ======================  Properties  =============================
    String fName,lName,password,email;
    
    // ======================== Constructors ============================
    public Person() {
        this.fName = "";
        this.lName = "";
        this.password = "";
        this.email = "";
    }
    public Person(String firstName, String lastName, String password, String eMail) {
        this.fName = firstName;
        this.lName = lastName;
        this.password = password;
        this.email = eMail;
    }
    
    // ==================================  Behaviors ===============================
    public void setFirstName(String firstName) {fName = firstName;}
    public String getFirstName() {return fName;}
    
    public void setLastName(String lastName) {lName = lastName;}
    public String getLastName() {return lName;}
    
    public String getName() {return fName + " " + lName;}
    
    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return password;}
    
    public void setEMail(String eMail) {email = eMail;}
    public String getEMail() {return email;}
    
    public void display() {
        System.out.println("First Name: " + getFirstName());
        System.out.println("Last Name: " + getLastName());
        System.out.println("Password:" + getPassword());
        System.out.println("E-Mail: " + getEMail());
    }
    
}
