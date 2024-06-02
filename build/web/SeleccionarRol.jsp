<%-- 
    Document   : SeleccionarRol
    Created on : 18 feb. 2024
    Author     : Cano's Code 
--%>

<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="./CSS/css.css">
    <link rel="stylesheet" href="./CSS/noramalize.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/002144bd49.js" crossorigin="anonymous"></script>
    <title>ShopEasy | Compra Facil Y Ahora</title>
</head>

<body>
    <form action="CompletarRegistroServlet" method="post">
        <section class="Roles">
            <span class="TituloSHOP1">¿Qué Rol Desenvuelves?</span>
            <div class="rol" onclick="seleccionarRol('CLIENTE')" id="cliente">
                <i class="fa-solid fa-user"></i>
                <h2>Soy Cliente</h2>
            </div>
            <div class="rol" onclick="seleccionarRol('DUENO')" id="dueño">
                <i class="fa-solid fa-store"></i>
                <h2>Soy Dueño</h2>
            </div>
            <div class="botonesNAV">
                <a href="index.jsp" class="button button-dark" >Atrás</a>
                <button href="" type="submit" value="Guardar Rol" class="button button-dark disable" id="submitBtn">Siguiente</button>
            </div>
        </section>
        <input type="hidden" name="rol" id="rol">
    </form>

    <script>
        function seleccionarRol(rol) {
            var selectRol = document.getElementById("rol");
            var submitBtn = document.getElementById("submitBtn");
            var clienteDIV = document.getElementById("cliente")
            var duennoDIV = document.getElementById("dueño")
            // Establecer el valor del select
            selectRol.value = rol;
    
            if(rol === "CLIENTE"){
                clienteDIV.classList.add("seleccionado");
                duennoDIV.classList.remove("seleccionado")
            }else if(rol === "DUENO"){
                duennoDIV.classList.add("seleccionado")
                clienteDIV.classList.remove("seleccionado");
            }
            // Activar o desactivar el botón según si se ha seleccionado un rol
            if (rol === "CLIENTE" || rol === "DUENO") {
                submitBtn.disabled = false;
                submitBtn.classList.remove("disable");

            } else {
                submitBtn.disabled = true;
                submitBtn.classList.add("disable");
            }

            console.log(rol)
        }
    </script>
</body>

</html>
