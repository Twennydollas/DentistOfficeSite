<%-- 
    Document   : login
    Created on : Apr 11, 2022
    Author     : Justin Rismiller
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ChattDent Login</title>
        <link rel="stylesheet" href="loginStyle.css" type="text/css" media="screen">
        <script src="validate.js" async></script>
        <script src="loginJS.js"  ></script>
    </head>
    <body id="body" onload="logTypeChange()">
        <main>
           <div id="login">
            <h1>Welcome to ChattDent!</h1>
                <div id="alignForm">
                    <form id= "loginForm" name="loginForm" action="loginServlet" method="post" onsubmit="return validateForm()">
                        <label for="loginType">Login Type: </label>	
                        <select id="loginType" name="loginType" oninput="logTypeChange()">
                            <option value="Select Login Type...">Select Login Type...</option>
                            <option value="Patient">Patient</option>
                            <option value="Dentist">Dentist</option>
                        </select>
                        <br><br>
                        <label for="loginID">Login ID: </label>		
                        <input type="text" placeholder="Enter Login ID" id="loginID" name="loginID">
                        <br><br>		
                        <label for="password">Password: </label>		
                        <input type="text" placeholder="Enter Password" id="password" name="password">
                        <br><br>	
                        
                        <input type="submit" id="loginBTN" value="Login" style="width: 70%;font-size: 200%;font-weight: bold;border: 4px solid;border-radius: 14px; background-color: lightblue;">
                    </form>
                </div>
            </div> 
        </main>
        
    </body>
</html>
