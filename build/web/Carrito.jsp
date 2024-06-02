<%-- 
    Document   : Carrito
    Created on : Mar 20, 2024, 12:08:02 AM
    Author     : Cano's Code
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page import="Control.CarritoDAO"%>
<%CarritoDAO carrito = new CarritoDAO();%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Easy Shop </title>
    </head>
    <body id="yellow1" style="background-color: #ffd155">
        <style>
            .container {
                max-width: 800px;
                margin: 10% auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
                text-align: center;
            }
            .producto {
                display: flex;
                align-items: center;
                border-bottom: 1px solid #ddd;
                padding: 10px 0;
            }
            .producto img {
                width: 100px;
                margin-right: 20px;
            }
            .producto .info {
                flex: 1;
            }
            .producto .nombre {
                font-weight: bold;
            }
            .producto .precio {
                color: #888;
            }
            .producto .cantidad {
                text-align: center;
                margin-right: 50px;
                width: 50px;
            }
            .total {
                margin-top: 20px;
                text-align: right;
            }

            .total1 {
                margin-top: 20px;
                text-align: right;
            }
            .boton {
                display: inline-block;
                background-color: #007bff;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                text-decoration: none;
            }
            .boton:hover {
                background-color: #0056b3;
            }
        </style>
        <%
            List<Producto> productos = carrito.obtenerCarrito(request);
        %>
        <%
            // Obtenemos todas las cookies de la solicitud actual
            Cookie[] cookies = request.getCookies();

            // Variable para verificar si se encontró la cookie
            boolean cookieEncontrada = false;

            // Verificamos si existen cookies
            if (cookies != null) {
                // Iteramos sobre todas las cookies
                for (Cookie cookie : cookies) {
                    // Verificamos si la cookie que buscamos existe
                    if (cookie.getName().equals("sessionId")) {
                        cookieEncontrada = true;
                        break; // Salimos del bucle ya que encontramos la cookie
                    }
                }
            }

            // Si no se encontró la cookie, redirigimos a la página de inicio de sesión
            if (!cookieEncontrada) {
                response.sendRedirect("inicio_sesion.html");
            }
        %>
        <script>
            function cerrarSesion() {
                // Crear una solicitud XMLHttpRequest
                var xhr = new XMLHttpRequest();

                // Definir la función de callback cuando se complete la solicitud
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        // Redirigir a la página de inicio de sesión después de cerrar sesión
                        window.location.href = "index.jsp";
                    }
                };

                // Realizar una solicitud POST al servlet para cerrar sesión
                xhr.open("POST", "CerrarSesionServlet", true);
                xhr.send();
            }
        </script>
        <header class="hrd1">
            <header class="hdr">
                <div class="logo-container">
                    <img src="./assets/Brand/logo.png" alt="Logo de la empresa" class="logo">
                    <span class="company-name">ShopEasy</span>
                </div>
                <nav class="nav-links">
                    <a href="pagina_inicio_cliente.jsp" class="button button-light">Home</a>
                    <a href="Carrito.jsp" class="button button-light">Carrito</a>
                    <a href="#" class="button button-dark" onclick="cerrarSesion()">Cerrar Sesión</a>
                </nav>
            </header>
        </header>

        <div class="container">
            <h1>Carrito de Compras</h1>

            <% for (Producto producto : productos) {%>
            <div class="producto">

                <% byte[] imageData = producto.getImagen();
                    String base64Image = imageData != null
                            ? java.util.Base64.getEncoder().encodeToString(imageData) : "";%>
                <img src="data:image/jpeg;base64, <%= base64Image%>" alt="<%=producto.getNombre()%>">
                <div class="info">
                    <div class="nombre"><%= producto.getNombre()%></div>
                    <div class="precio">$ <%= producto.getPrPrecio()%></div>
                </div>
                <input type="number" class="cantidad" value="1" min="1">
                <button class="boton" onclick="eliminarDelCarrito(<%= producto.getIdProducto()%>)">Eliminar</button>

            </div>
            <%}%>
            <script>
                function eliminarDelCarrito(producto) {
                    // Realizar una llamada AJAX para eliminar el producto del carrito
                    var xhr = new XMLHttpRequest();
                    xhr.open("GET", "CarritoServlet?accion=eliminar&producto=" + encodeURIComponent(producto), true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            // Manejar la respuesta si es necesario
                            console.log("Producto eliminado del carrito: " + producto);
                            // Recargar la página después de eliminar el producto
                            window.location.reload();
                        }
                    };
                    xhr.send();
                }
            </script>
            <div class="total1">
                <button class="boton" onclick="comprar()">Comprar</button>
            </div>

            <div class="total">
                Total: $<span class="total33" id="total33">0.00</span>
            </div>
            <script>
                // Función para calcular el total automáticamente
                function calcularTotal() {
                    console.log("Hola")
                    var total = 0;
                    var productos = document.querySelectorAll('.producto');
                    productos.forEach(function (producto) {
                        var precio = parseFloat(producto.querySelector('.precio').innerText.replace('$', ''));
                        var cantidad = parseInt(producto.querySelector('.cantidad').value);
                        total += precio * cantidad;
                    });
                    // Actualizar el valor total en el DOM
                    document.querySelector('.total33').innerText = total.toFixed(2);
                }

                // Llamar a la función calcularTotal cuando se carga la página
                window.addEventListener('load', calcularTotal);
                // Llamar a la función calcularTotal cuando cambia la cantidad de un producto
                var cantidades = document.querySelectorAll('.cantidad');
                cantidades.forEach(function (cantidad) {
                    cantidad.addEventListener('change', calcularTotal);
                });
            </script>

            <script>
                function comprar() {

                    var cantidades = [];
                    var inputsCantidades = document.querySelectorAll('.producto .cantidad');
                    inputsCantidades.forEach(function (input) {
                        cantidades.push(input.value);
                    });
                    console.log("La cantidad de productos son: " + cantidades[0]);

                    let total = document.getElementById("total33").textContent;
                    if (total) {
                        console.log("El Total es: " + total);
                    } else {
                        console.error("No se encontró el elemento con el id 'total33'");
                    }

                    // Construir objeto de datos para enviar por AJAX
                    var datos = {
                        cantidades: cantidades,
                        total: total
                    };

                    // Enviar datos al servidor usando AJAX
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "procesarCompra2.jsp", true);
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            console.log("Respuesta del servidor:", xhr.responseText);

                            // Realizar una llamada AJAX para Borrar Todo El Carrito
                            var xhr1 = new XMLHttpRequest();
                            xhr1.open("GET", "CarritoServlet?accion=BorrarCarrito", true);
                            xhr1.onreadystatechange = function () {
                                if (xhr1.readyState === 4 && xhr1.status === 200) {
                                    // Manejar la respuesta si es necesario
                                    console.log("Se eliminó El Carrito");
                                }
                            };
                            xhr1.send();

                            // Redireccionar a una página JSP 
                            window.location.href = "pagina_inicio_cliente.jsp";
                        }
                    };
                    xhr.send(JSON.stringify(datos));
                }
            </script>
    </body>
</html>