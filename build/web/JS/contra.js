/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let input = document.getElementById("password");
let nver = document.getElementById("nver");
let ver = document.getElementById("ver");
ver.addEventListener("click", ()=>{ 
    input.type = "text";
    ver.style.display = "none";
    nver.style.display = "block";
});
nver.addEventListener("click", ()=>{ 
    input.type = "password";
    ver.style.display = "block";
    nver.style.display = "none";
});
