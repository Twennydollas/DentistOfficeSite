/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

/**
 *Created on : Apr 13, 2022
 *  Author     : Justin Rismiller    
 */
public class ProcedureList {
    public int count = 0;
    public Procedure procArr[] = new Procedure[8];
    
    public void addProcedure(Procedure p1){
        procArr[count] = p1;        
        count++;
        System.out.println("Procedure added to list");
    }
    
    public void displayList() {
        for(int x = 0; x < count; x++){
            procArr[x].display();
        }
        
    }
}
