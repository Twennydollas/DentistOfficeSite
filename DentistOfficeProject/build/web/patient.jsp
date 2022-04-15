<%-- 
    Document   : patient
    Created on : Apr 11, 2022, 3:30:22 PM
    Author     : Justin Rismiller
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="Business.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Page</title>
        <link rel="stylesheet" href="patientStyle.css">
	<script src="validate.js" async></script>
    </head>
    <body>
        <%
        Patient p1 = (Patient)session.getAttribute("p1");
        String name,fName,lName,addr,pass,email,ins, cpass; 
        String procID, procName, dentID, apptTime, dentName;
        Appointment a1 = new Appointment();
        Procedure pr1 = new Procedure();
        Dentist d1 = new Dentist();
        
        a1.selectDB(p1.getPatientID());
        pr1.selectDB(a1.getProcedureCode());
        d1.selectDB(a1.getDentistID());
        
        System.out.println("Patient loaded from session");
        p1.display();
        
        name = p1.getName();
        fName = p1.getFirstName();
        lName = p1.getLastName();
        addr = p1.getAddress();
        pass = p1.getPassword();
        email = p1.getEMail();
        ins = p1.getInsurance();
        procID = a1.getProcedureCode();
        procName = pr1.getProcedureName();
        apptTime = a1.getAppointmentTime();
        dentID = a1.getDentistID();
        dentName = d1.getName();
        %>
        <div id="bodyBox">
            
            
            
            <div id="appointmentInfo">
                <h1>Hello <%= name%> !</h1><hr>
                 <table id="apptTable">
                <tr>
                <th colspan="3" style="background-color: grey"><i>Current Appointment</i></th>                            
                </tr>
                         <tr>
                        <th style="font-size: 18px"><i>Appointment Time</th>
                        <th style="font-size: 18px"><i>Procedure</th>
                        <th style="font-size: 18px"><i>Dentist</th>
                        </tr>
                        <%
                                        out.println("<td>"+apptTime+"</td>");
                                        out.println("<td>"+procName+"</td>"); 
                                        out.println("<td>"+dentName+"</td>");                                                        
                                %>                     
                </table><br>
                
                <h2>Change Appointment</h2>
                
                <form id="appointmentInfoForm" name="appointmentInfoForm" action="PatientServlet">
                    <label for="apptYear">Select Appointment Year:</label>
                    <select id="apptYear" name="apptYear">
                        <%                            
                            Calendar calendar = Calendar.getInstance();  
                            Date d = new Date();
                            for(int x = 0; x <= 5; x++){                                                                
                                //d = calendar.getTime();                                
                                out.println("<option value=\""+ calendar.get(Calendar.YEAR) +"\">"+calendar.get(Calendar.YEAR)+"</option>");
                                calendar.add(Calendar.YEAR,+1);
                                }
                        %>  
                        </select><br><br>
                    <label for="apptMonth">Select Appointment Month:</label>
                    <select id="apptMonth" name="apptMonth" onload="monthNames()">
                        <%          
                            calendar.set(Calendar.MONTH,0);
                            for(int x = 0; x <= 11; x++){                                                                
                                d = calendar.getTime();
                                SimpleDateFormat DateFor = new SimpleDateFormat("MMM");
                                String month= DateFor.format(d);
                                out.println("<option value=\""+ month +"\">"+month+"</option>");
                                calendar.add(Calendar.MONTH,+1);
                                }
                        %>  
                    </select><br><br>
                    <label for="apptDay">Select Appointment Day:</label>
                    <select id="apptDay" name="apptDay">
                        <%   
                            calendar.set(Calendar.DAY_OF_MONTH, calendar.getMinimum(Calendar.DAY_OF_MONTH));
                            for(int x = 0; x < calendar.getMaximum(Calendar.DAY_OF_MONTH); x++){
                                out.println("<option value=\""+ calendar.get(Calendar.DAY_OF_MONTH)+"\">"+calendar.get(Calendar.DAY_OF_MONTH)+"</option>");
                                calendar.add(Calendar.DAY_OF_MONTH,+1);
                                }
                        %>  
                    </select><br><br>
                    <label for="apptTime">Select Appointment Time:</label>
                    <select id="apptTime" name="apptTime">
                        <%         
                            calendar.set(Calendar.HOUR, 9);
                            calendar.set(Calendar.AM_PM, 0);
                            
                            SimpleDateFormat hourFormat = new SimpleDateFormat("hh");
                            
                            for(int x = 0; x < 8; x++){
                                d = calendar.getTime();
                                String hour= hourFormat.format(d);
                                int AmPm = calendar.get(Calendar.AM_PM);
                                String AMPM = "";
                                if (AmPm == 0 ){AMPM = "AM";}
                                else{AMPM = "PM";}
                                out.println("<option value=\""+ hour+" "+AMPM+"\">"+hour+" "+AMPM+"</option>");
                                calendar.add(Calendar.HOUR,+1);
                                }
                                
                        %>                        
                    </select><br><br>
                    <label for="dentistSel">Select Dentist:</label>
                    <select id="dentistSel" name="dentistSel">
                        <%   
                            d1.getDentistList();
                            for(int x = 0; x < d1.dlist.count; x++){
                                dentID = d1.dlist.dentArr[x].getDentistID();
                                dentName = d1.dlist.dentArr[x].getName();
                                out.println("<option value=\""+ dentID+"\">"+dentName+"</option>");
                                }
                        %>  
                    </select><br><br>
                    <label for="procedureSel">Select Procedure:</label>
                    <select id="procedureSel" name="procedureSel">
                        <%                            
                            pr1.getProcedureList();
                            for(int x = 0; x < pr1.plist.count; x++){
                                procID = pr1.plist.procArr[x].getProcedureCode();
                                procName = pr1.plist.procArr[x].getProcedureName();
                                out.println("<option value=\""+ procID+"\">"+procName+"</option>");
                                }
                        %>                        
                    </select><br><br>
                    <input type="hidden" id="patientFunction" name="patientFunction" value="apptInfo">
                    <input type="submit" id="apptBTN" value="Add New / Change Appointment" style="width: 70%;font-size: 120%;font-weight: bold;border: 4px solid;border-radius: 14px; background-color: lightblue;">
                </form>                        
            </div>
            
            <div id="patinetInfo">
                
                
                <div id="currentInfo">
                    <h2 style="text-align: center;">Current Information</h2>
                    <h4>First Name: <%= fName%></h4>
                    <h4> Last Name: <%=lName%></h4>
                    <h4>Address: <%= addr%></h4>
                    <h4>Email: <%=email%></h4>
                    <h4>Insurance Company: <%= ins%></h4>
                    <h4>Password: <%=pass%></h4><br>
                </div>
                <h2>Change Information</h2>
                <form id="patinetInfoForm" name="patinetInfoForm" onsubmit="return validatePatientInfo()" action="PatientServlet">
                    <label for="fname">Change First Name:</label>
                    <input type="text" placeholder="Enter First Name" id="fname" name="fname" value="<%= fName%>"><br><br>
                    <label for="lname">Change Last Name:</label>
                    <input type="text" placeholder="Enter Last Name" id="lname" name="lname" value="<%= lName%>"><br><br>
                    <label for="addr">Change Address:</label>
                    <input type="text" placeholder="Enter Address" id="addr" name="addr" value="<%= addr%>"><br><br>
                    <label for="eMail">Change Email:</label>
                    <input type="text" placeholder="Enter Email Address" id="eMail" name="eMail" value="<%= email%>"><br><br>
                    <label for="insur">Change Insurance Company:</label>
                    <input type="text" placeholder="Enter Insurance Company" id="insur" name="insur" value="<%= ins%>"><br><br>
                    <label for="pass">Change Password:</label>
                    <input type="text" placeholder="Enter Password" id="pass" name="pass" value="<%= pass%>"><br><br> 
                    <label for="cpass">Confirm Change Password:</label>
                    <input type="text" placeholder="Re-Enter Password" id="cpass" name="cpass" value=""><br><br>
                    <input type="hidden" id="patientFunction" name="patientFunction" value="changePatientInfo">
                    <input type="submit" id="infoBTN" value="Change Info" style="width: 70%;font-size: 120%;font-weight: bold;border: 4px solid;border-radius: 14px; background-color: lightblue;">
                </form> <br>               
            </div>
           
            
        </div>
    </body>
</html>
