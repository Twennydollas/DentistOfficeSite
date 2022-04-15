/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
/* 
    Created on : Apr 11, 2022
    Author     : Justin Rismiller
*/

function validateForm() {

    let loginID = document.loginForm.loginID.value;
    let password = document.loginForm.password.value;
    let logType = document.loginForm.loginType.value;

    switch(logType){

      case("Select Login Type..."):
         alert("Please enter the correct login Type in the text box");
            return false;


      case("Patient"):
          if (isNaN(password)){
        alert("Please enter the correct password in text box");
        return false;
      }

      if (password.length>4 || password.length<=3){
        alert("Please enter the correct login Password in the text box");
        return false;
      }


      case("Dentist"):        


      default :
          if (loginID ===""){
        alert("Please enter the correct login ID in the text box");
        return false;
      }   

      if (password === null || password === "" ){
        alert("Please enter the correct password in text box");
        return false;
      }

      break;
      }
  }

  
  function validatePatientInfo(){
        let password = document.patinetInfoForm.pass.value;
        let cpassword = document.patinetInfoForm.cpass.value;
         if (password !== cpassword){
        alert("password and confirm password must match");
        return false;
      }
    }
   function validateDentistInfo(){
        let password = document.getElementById("pass").value;
        let cpassword = document.getElementById("cpass").value;
         if (password !== cpassword){
        alert("password and confirm password must match");
        return false;
      }
    }