/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

/**
 *Created on : Apr 13, 2022
 *  Author     : Justin Rismiller    
 */
public class AppointmentList {
   
    public int count = 0;
    public Appointment apptArr[] = new Appointment[200];
    
    public void addAppointment(Appointment a1){
        apptArr[count] = a1;        
        count++;
        System.out.println("Appointment added to list");
    }
    
    public void displayList() {
        for(int x = 0; x < count; x++){
            apptArr[x].display();
        }
        
    }
    
}
