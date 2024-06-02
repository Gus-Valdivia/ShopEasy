<%-- 
    Document   : Lista Pedido
    Created on : 27 nov. 2023, 07:06:28
    Author     : KONER
--%>
<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.util.*" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Pedido</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./CSS/styleListaPedido.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="CSS/General/nav.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="CSS/General/normalize.css">
    <link rel="stylesheet" href="CSS/General/index.css">
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
</head>
    <<header class="d-flex w-100 justify-content-between p-2 align-items-center hdr">
        <section class="d-flex align-items-center ">
            <img src="assets/pngwing.com (12).png" class="logo">
            <a href="pagina_inicio_cliente.jsp">ShopEasy</a>
        </section>
        <section>
            <a href="pagina_inicio_cliente.jsp" class="btn btn-light btn_nav btn_navs"><i
                    class="bi bi-house-door-fill"></i> Home</a>
            <a href="#" class="btn btn-dark btn_nav btn_navr">Favoritos</a>
        </section>
    </header>body style="overflow: hidden"; >
    

        <nav class="nav container" id="nav">
            <a href="#nav" class="nav__hamburguer">
                <img src="./assets/menu.png" class="nav__icon">
            </a>
            <a href="#" class="nav__close">
                <img src="./assets/close.png" class="nav__icon">
            </a> 
            <h2 class="nav__logo">ShopEasy</h2>
    
            <ul class="nav__links">
                <h3>
                <li class="nav__item">
                    <a href="./Productos.jsp" class="nav__link">Productos</a>
                </li>
                <li class="nav__item">
                    <a href="#" class="nav__link">Favoritos</a>
                </li>
                <li class="nav__item">
                    <a href="./Inventario.jsp" class="nav__link">Inventario</a>
                </li>
                <li class="nav__item">
                    <a href="./ListaProductos.jsp" class="nav__link">Lista de Productos</a>
                </li>
                </h3>
            </ul>
        </nav>
        

        
        <h2 class="tit">Lista Productos</h2>
        <div class="d_solicitud">
        <div class="productos">
                <p>Nombre del Producto: ${nombreProducto}</p>
                <p>Cantidad: ${cantidad}</p>
        </div>
        <form action="/EasyShop/EnviarSolicitudProveedorServlet" method="post">       
    <input type="hidden" name="nombreProducto" value="${nombreProducto}">
    <input type="hidden" name="cantidad" value="${cantidad}">
    <button type="submit" class="inpS">Enviar Solicitud al Proveedor</button>
</form>    
   <script src="JS/bubble.js"></script>
</body>
</html>

