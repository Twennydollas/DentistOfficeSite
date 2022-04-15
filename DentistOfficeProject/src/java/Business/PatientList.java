/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

/**
 *Created on : Apr 13, 2022
 *  Author     : Justin Rismiller    
 */
public class PatientList {
    public int count = 0;
    public Patient patArr[] = new Patient[200];
    
    public void addPatient(Patient p1){
        patArr[count] = p1;        
        count++;
        System.out.println("Patient added to list");
    }
    
    public void displayList() {
        for(int x = 0; x < count; x++){
            patArr[x].display();
        }
        
    }
    
}
