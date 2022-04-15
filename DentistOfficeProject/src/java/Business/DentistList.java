/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

/**
 *Created on : Apr 13, 2022
 *  Author     : Justin Rismiller    
 */
public class DentistList {
    public int count = 0;
    public Dentist dentArr[] = new Dentist[200];
    
    public void addDentist(Dentist a1){
        dentArr[count] = a1;        
        count++;
        System.out.println("Dentist added to list");
    }
    
    public void displayList() {
        for(int x = 0; x < count; x++){
            dentArr[x].display();
        }
        
    }
}
