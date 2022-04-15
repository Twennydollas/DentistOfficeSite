<%-- 
    Document   : dentist
    Created on : Apr 11, 2022, 8:37:02 PM
    Author     : Justin Rismiller
--%>

<%@page import="Business.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dentist Page</title>
        <link rel="stylesheet" href="dentistStyle.css">
        <script src="validate.js" async></script>
    </head>
    <body>
        <%
        Dentist d1 = (Dentist)session.getAttribute("d1");
        Appointment a1 = new Appointment();
        Procedure pr1 = new Procedure();
        Patient p1 = new Patient();
        
        System.out.println("Dentist loaded from session");
        d1.display();
        
        
        
        String name, fName, lName, pass, email, office, dID, procID, procName, apptTime, patID, patName; 
        name = d1.getName();
        fName = d1.getFirstName();
        lName = d1.getLastName();
        office = d1.getOffice();
        email = d1.getEMail();        
        pass = d1.getPassword();
        
        apptTime="";
        procName="";
        patName ="";
        %>
       
        <div id="bodyBox">
            <h1>Hello <%= name%> !</h1><hr>
            <table id="apptTable">
                <tr>
                <th colspan="3"><i>Current Appointments</i></th>                            
                </tr>
                         <tr>
                        <th style="font-size: 18px"><i>Appointment Time</th>
                        <th style="font-size: 18px"><i>Procedure</th>
                        <th style="font-size: 18px"><i>Patient</th>
                        </tr>
                        <%
                            
                            
                            a1.getAppointmentList();
                            for(int x=0;x<a1.alist.count;x++){
                                patID = a1.alist.apptArr[x].getPatientID();
                                
                               
                                
                                dID = a1.alist.apptArr[x].getDentistID();
                                System.out.println(dID +":"+ d1.getDentistID());
                                    if(dID.equals(d1.getDentistID()) ){ 
                                        if(patID!=null||patID!=""){
                                        procID = a1.alist.apptArr[x].getProcedureCode();
                                        a1.selectDB(patID);
                                        p1.selectDB(patID);
                                        if(procID!=null||procID!=""){
                                            pr1.selectDB(procID);
                                        }
                                        procName = pr1.getProcedureName();
                                        apptTime = a1.alist.apptArr[x].getAppointmentTime();                                    
                                        patName = p1.getName();  
                                        System.out.println(procName +":"+patName+":"+patName);
                                        out.println("<tr>");
                                        out.println("<td>"+apptTime+"</td>");
                                        out.println("<td>"+procName+"</td>"); 
                                        out.println("<td>"+patName+"</td>"); 
                                        out.println("</tr>");

                                        }
                                }
                                    
                                   
                                    
                                   
                            }
                            
                            
                                       
                                %>                     
            </table><br>
            <div id="dentistInfo">
                
                <div id="currentInfo">
                    <h2 style="text-align: center;">Current Information</h2>
                    <h4>First Name: <%= fName%></h4>
                    <h4> Last Name: <%=lName%></h4>
                    <h4>Email: <%=email%></h4>
                    <h4>Office Number: <%= office%></h4>
                    <h4>Password: <%=pass%></h4><br>
                </div>
                    
                <h2>Change Information</h2>    
                <form id="dentistInfoForm" name="dentistInfoForm" onsubmit="return validateDentistInfo()" action="DentistServlet"> 
                    
                    <label for="fname">Change First Name: </label>
                    <input type="text" placeholder="Enter First Name" id="fname" name="fname" value="<%= fName%>"><br><br>
                    <label for="lname">Change Last Name: </label>
                    <input type="text" placeholder="Enter Last Name" id="lname" name="lname" value="<%= lName%>"><br><br>
                    <label for="eMail">Change Email: </label>
                    <input type="text" placeholder="Enter Email Address" id="eMail" name="eMail" value="<%= email%>"><br><br>
                    <label for="office">Change Office Number: </label>
                    <input type="text" placeholder="Enter Office Number" id="office" name="office" value="<%= office%>"><br><br>
                    <label for="pass">Change Password: </label>
                    <input type="text" placeholder="Enter Password" id="pass" name="pass" value="<%= pass%>"><br><br> 
                    <label for="cpass">Confirm Change Password:</label>
                    <input type="text" placeholder="Re-Enter Password" id="cpass" name="cpass" value=""><br><br>
                    <input type="submit" id="infoBTN" value="Change Info" style="width: 70%;font-size: 120%;font-weight: bold;border: 4px solid;border-radius: 14px; background-color: lightblue;">
                </form> <br>               
            </div>
        </div>
    </body>
</html>
