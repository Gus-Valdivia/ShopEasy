<%-- 
    Document   : pagina_inicio_cliente
    Created on : 18 nov. 2024
    Author     : Cano's Code
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Control.ProductoDAO"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Producto"%>
<%@page import="Modelo.Producto"%>
<%
    ProductoDAO productoDAO = new ProductoDAO();
    List<Producto> todosLosProductos = productoDAO.obtenerTodosLosProductosST();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="CSS/General/normalize.css">
        <link rel="stylesheet" href="CSS/Datos/iniciar_sesion.css">
        <link rel="stylesheet" href="CSS/css.css">
        <link rel="stylesheet" href="./CSS/styleInicioSesion.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/002144bd49.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <title>ShopEasy | Compra Facil Y Ahora</title>
    </head>
    <body>
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
        <header class="hrd1" id="is_hdr">
            <header class="hdr" id="is_hdr">
                <a class="logo-container" href="pagina_inicio_cliente.jsp" id="logo_cont_homecl">
                    <img src="./assets/Brand/logo.png" alt="Logo de la empresa" class="logo">
                    <span class="company-name">ShopEasy</span>
                </a>
                <nav class="nav-links">
                    <a href="pagina_inicio_cliente.jsp" class="button"><i class="bi bi-house-door-fill"></i> Inicio</a>
                    <a href="Carrito.jsp" class="button"><i class="bi bi-cart-fill"></i> Carrito</a>
                    <a href="#" class="button button-dark" onclick="cerrarSesion()">Cerrar Sesión <i class="bi bi-door-open-fill"></i></a>
                </nav>
            </header>
        </header>
        <div class="panel-izquierdo">
            <h2>Categorías</h2>
            <hr>
            <ol>
                <li onclick="irAProductos('1')"><i class="fa-solid fa-glass-water"></i> <div>Agua</div></li>
                <li onclick="irAProductos('2')"><i class="fa-solid fa-basket-shopping"></i> <div>Despensa</div></li>
                <li onclick="irAProductos('3')"><i class="fa-solid fa-candy-cane"></i> <div>Dulces</div></li>
                <li onclick="irAProductos('4')"><i class="fa-solid fa-soap"></i> <div>Higiene</div></li>
                <li onclick="irAProductos('5')"><i class="fa-solid fa-bread-slice"></i> <div>Panes</div></li>
                <li onclick="irAProductos('6')"><i class="fa-solid fa-bottle-droplet"></i> <div>Refrescos</div></li>
            </ol>
        </div>

        <div class="panel-derecho">
            <section class="section-container">
                <span class="bnv_es">Se Bienvenido a <span>ShopEasy</span></span>
                <div class="TituloPrincipal">
                    <span class="TituloSHOP">¡La mejor experiencia de compra, justo a tu alcance!</span>
                </div>
                <div class="Comentario">
                    <span class="desc_neg">¡Te damos la bienvenida a ShopEasy, tu aliado para una compra excepcional! Ofrecemos una amplia variedad de productos de alta calidad, asegurando que encuentres siempre lo que necesitas. Con un equipo especializado, te brindamos un servicio personalizado y resolvemos tus dudas para que tu experiencia sea rápida y sencilla. Además, te facilitamos la compra en el mundo digital. ¡Descubre cómo hacer tus compras más fáciles con ShopEasy!</span>
                </div>
            </section>
            <span class="TituloSHOP" id="txt">¡Bienvenido a ShopEasy!</span>

            <script>
                function search_product() {
                    let input = document.getElementById('inputbuscar').value
                    input = input.toLowerCase();
                    let x = document.getElementsByClassName('productCard1');
                    for (i = 0; i < x.length; i++) {
                        if (!x[i].innerHTML.toLowerCase().includes(input)) {
                            x[i].style.display = "none";
                        } else {
                            x[i].style.display = "flex";
                        }
                    }
                }
            </script>

            <section class="ProductosDisplay">
                <div class="buscador">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <input type="text" id="inputbuscar" placeholder="Buscar productos... " onkeyup="search_product()">
                </div>
                <% for (Producto producto : todosLosProductos) {%>
                <div class="productCard1" onclick="irCompra(<%= producto.getIdProducto()%>)">
                    <% byte[] imageData = producto.getImagen();
                        String base64Image = imageData != null
                                ? java.util.Base64.getEncoder().encodeToString(imageData) : "";%>
                    <section class="img_pro_card">
                        <img class="img_log_pc" src="assets/Brand/logo.png" alt="alt"/>
                        <img src="data:image/jpeg;base64, <%= base64Image%>" alt="<%= producto.getNombre()%>">
                    </section>
                    <h3><%= producto.getNombre()%></h3>
                    <p>$ <span><%=producto.getPrPrecio()%></span></p>
                </div>
                <% }%>
            </section>
            <footer class="footer">
                <div class="footer-container">
                    <div class="footer-section">
                        <h3>Enlaces útiles</h3>
                        <ul>
                            <li><a href="/index.html">Inicio</a></li>
                            <li><a href="#">Productos</a></li>
                            <li><a href="#">Servicios</a></li>
                        </ul>
                    </div>
                    <div class="footer-section">
                        <h3>Contacto</h3>
                        <p>Email: info@example.com</p>
                        <p>Teléfono: +1234567890</p>
                    </div>
                </div>
                <div class="footer-bottom">
                    <p>&copy; 2024 Easy Shop. Todos los derechos reservados.</p>
                </div>
            </footer>
        </div>

        <script>
            function irAProductos(tipoProducto) {
                window.location.href = './Productos.jsp?tipo=' + tipoProducto;
            }

            function irCompra(idProducto) {
                window.location.href = 'compraCliente.jsp?productoId=' + idProducto;
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="JS/bubble_1.js"></script>
    </body>
</html>



