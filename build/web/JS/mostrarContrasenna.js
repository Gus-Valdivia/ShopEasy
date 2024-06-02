/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.getElementById("showPasswordButton").addEventListener("click", function () {
    var passwordInput = document.getElementById("password");
    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        this.textContent = "Ocultar";
    } else {
        passwordInput.type = "password";
        this.textContent = "Mostrar";
    }
});
