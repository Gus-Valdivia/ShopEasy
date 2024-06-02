<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.util.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Mensaje proveedor</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./CSS/styleMensaje.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
</head>
    <body style="overflow: hidden"; >
    <header class="hero">
        
        <svg class="fig1" width="492" height="345" viewBox="0 0 526 462" fill="none">
            <g style="mix-blend-mode:soft-light">
            <ellipse cx="560" cy="-73" rx="560" ry="414" fill="#D9D9D9"/>
            </g>
        </svg>
        <svg class="fig3" width="422" height="286" viewBox="0 0 422 286" fill="none">
            <g style="mix-blend-mode:soft-light">
            <ellipse cx="284.5" cy="276" rx="284.5" ry="276" fill="#D9D9D9"/>
            </g>
        </svg>
        <svg class="fig2" width="669" height="444" viewBox="0 0 712 650" fill="none" >
        <g style="mix-blend-mode:soft-light">
        <ellipse cx="327" cy="218" rx="327" ry="218" fill="#D9D9D9"/>
        </g>
        </svg>

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
                    <a href="#" class="nav__link"></a>
                </li>
                <li class="nav__item">
                    <a href="#" class="nav__link"></a>
                </li>
                </h3>
            </ul>
        </nav>
        

        
        <h2 class="tit">Pedido pendiente</h2>
        <div id="contenido-solicitud" class="d_solicitud">
            <!-- Contenido inicial -->
            <div class="message">
                <h1>Detalle de la solicitud:</h1>
                <p>Nombre del Producto: ${nombreProducto}</p>
                <p>Cantidad solicitada: ${cantidad}</p>

                <h2>Acciones:</h2>
                <form id="respuestaForm" action="/EasyShop/ProcesarRespuestaProveedorServlet" method="post">
                    <input type="hidden" name="nombreProducto" value="${nombreProducto}">
                    <input type="hidden" name="cantidad" value="${cantidad}">
                    <button type="submit" name="accion" value="aceptar">Aceptar</button>
                    <button type="submit" name="accion" value="rechazar">Rechazar</button>
                </form>
            </div>
        </div>
        
        
                    
    </header>
</body>
</html>
