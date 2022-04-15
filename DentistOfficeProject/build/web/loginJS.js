/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/**
 *Created on : Apr 13, 2022
 *  Author     : Justin Rismiller    
 */
    function logTypeChange() {
        let logType = document.loginForm.loginType.value;
        let logID = document.getElementById("loginID");
        let logPass = document.getElementById("password");
        switch(logType){

          case("Select Login Type..."):
             document.body.style.backgroundImage = "url('images/pexels-anna-shvets-3845729.jpg')";/*Photo by Anna Shvets from Pexels*/
             logID.value = "";
             logPass.value = "";
             break;

          case("Patient"):
             document.body.style.backgroundImage = "url('images/pexels-anna-shvets-3845855.jpg')";/*Photo by Anna Shvets from Pexels*/
             logID.value = "";
             logPass.value = "";
             break;

          case("Dentist"):        
              document.body.style.backgroundImage = "url('images/pexels-cottonbro-6502633.jpg')";/*Photo by cottonbro: https://www.pexels.com/photo/close-up-shot-of-a-denture-being-fitted-in-a-dental-equipment-6502633/*/
              logID.value = "";
              logPass.value = "";
              break;
          default :

          break;
        }
    }
    