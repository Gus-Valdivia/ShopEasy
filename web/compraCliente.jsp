<%-- 
    Document   : compraCliente
    Created on : 18 feb
    Author     : Cano's Code
--%>

<%@page import="Modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page import="Control.ProductoDAO"%>
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
            <% String productoId = request.getParameter("productoId"); %>


            <%
                if (productoId != null && !productoId.isEmpty()) {
                    int idProducto = Integer.parseInt(productoId);

                    ProductoDAO productoDAO = new ProductoDAO();

                    Producto producto = productoDAO.obtenerProductoPorId(idProducto);

                    if (producto != null) {

            %>
            <section class="productCard2">
                <div class="imgProduct">
                    <img src="data:image/jpeg;base64, <%= java.util.Base64.getEncoder().encodeToString(producto.getImagen())%>" alt="<%= producto.getNombre()%>">
                </div>
                <div class="datosProducto">
                    <div class="DatosCompra">
                        <h2><%= producto.getNombre()%></h2>
                        <h3>$ <span><%= producto.getPrPrecio()%></span></h3>
                        <h4>En Existencia: <span><%= producto.getCantidad()%></span></h4>
                        <button class="button button-dark" id="btnMostrarModal">Comprar</button>
                        <button class="button button-light" id="btnMostrarModal" onclick="agregarAlCarrito(<%=productoId%>)">Añadir Al Carrito</button>
                    </div>
                    <script>
                        function agregarAlCarrito(producto) {
                            // Realizar una llamada AJAX para agregar el producto al carrito
                            var xhr = new XMLHttpRequest();
                            xhr.open("GET", "CarritoServlet?accion=agregar&producto=" + encodeURIComponent(producto), true);
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                    // Manejar la respuesta si es necesario
                                    console.log("Producto agregado al carrito: " + producto);
                                }
                            };
                            xhr.send();
                        }
                    </script>
                    <div class="descripcionPRODUCTO">
                        <p><%= producto.getDescripcion()%></p>
                    </div>
                </div>
            </section>
            <%
            } else if (producto.getCantidad() == 0) {

            %>
            <section class="productCard2">
                <div class="imgProduct">
                    <img src="data:image/jpeg;base64, <%= java.util.Base64.getEncoder().encodeToString(producto.getImagen())%>" alt="<%= producto.getNombre()%>">
                </div>
                <div class="datosProducto">
                    <div class="DatosCompra">
                        <h2><%= producto.getNombre()%></h2>
                        <h3>$ <span><%= producto.getCosto()%></span></h3>
                        <h4>En Existencia: <span><%= producto.getCantidad()%></span></h4>
                        <button class="button button-dark" id="btnMostrarModal">Comprar</button>
                        <button class="button button-light">Agregar Al Carrito</button>
                    </div>
                    <div class="descripcionPRODUCTO">
                        <p><%= producto.getDescripcion()%></p>
                    </div>
                </div>
            </section>
            <%
            } else {

            %>
            <p class="error">No se ha proporcionado un ID de producto válido.</p>
            <%           }

                List<Producto> productos = productoDAO.getProductosPorTipo(producto.getTipo());
                productos.remove(productoDAO.obtenerProductoPorId(idProducto));
            %>
            <hr>
            <span class="TituloSHOP2">Articulos Relacionados</span>
            <section class="ProductosDisplay">
                <% for (Producto producto1 : productos) {%>
                <div class="productCard1" onclick="irCompra(<%= producto1.getIdProducto()%>)">
                    <% byte[] imageData = producto1.getImagen();
                        String base64Image = imageData != null
                                ? java.util.Base64.getEncoder().encodeToString(imageData) : "";%>
                    <img src="data:image/jpeg;base64, <%= base64Image%>" alt="<%=producto1.getNombre()%>">
                    <h3><%=producto1.getNombre()%></h3>
                    <p>$ <span><%=producto1.getPrPrecio()%></span></p>
                </div>
                <% }%>
            </section>
        </div>
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
        <div id="miModal" class="modal">
            <div class="modal-contenido">
                <span class="cerrar-modal" id="cerrarModal">&times;</span>
                <h2>¿Deseas Continuar Con La Compra?</h2>
                <form action="/EasyShop/RealizarCompraServlet" method="POST">
                    <div class="productoDetalles">
                        <img src="data:image/jpeg;base64, <%= java.util.Base64.getEncoder().encodeToString(producto.getImagen())%>" alt="<%= producto.getNombre()%>">
                        <h3><%= producto.getNombre()%></h3>
                        <h4>Precio: $ <span><%= producto.getPrPrecio()%></span></h4>
                        <label for="id"><h4>Disponible: <%= producto.getCantidad()%></h4></label>
                        <input type="text" name="categoria" value="<%= producto.getCategoria_idCategoria() %>" hidden="true">
                        <input type="text" name="id" value="<%= idProducto%>" hidden="true">
                        <input type="number" id="cantidadInput" name="cantidad">
                        <input type="number" id="total" name="total" value="<%= producto.getPrPrecio()%>" hidden="true">
                    </div>
                    <button class="button button-dark" type="submit">Aceptar</button>
                </form>
            </div>
        </div>
        <% String param = request.getParameter("param"); %>


        <% if (param != null && param.equals("true")) { %>
        <script>

            alert("¡Los parametros estan incompletos!");
        </script>
        <% }
            }%>

        <script>

            function validarCantidad() {
                var cantidadInput = document.getElementById("cantidadInput");
                var cantidad = cantidadInput.value;

                if (isNaN(cantidad) || cantidad <= 0) {
                    alert("Por favor, ingrese una cantidad válida mayor que cero.");
                    cantidadInput.value = "";
                    return false;
                }
                return true;
            }

            var form = document.querySelector('form');
            form.addEventListener('submit', function (event) {
                if (!validarCantidad()) {
                    event.preventDefault();
                }
            });
        </script>
        <script>
            function irAProductos(tipoProducto) {
                window.location.href = './Productos.jsp?tipo=' + tipoProducto;
            }

            function irCompra(idProducto) {
                window.location.href = 'compraCliente.jsp?productoId=' + idProducto;
            }
        </script>
        <script>
            document.getElementById("btnMostrarModal").addEventListener("click", function () {
                document.getElementById("miModal").style.display = "block";
            });

            document.getElementById("cerrarModal").addEventListener("click", function () {
                document.getElementById("miModal").style.display = "none";
            });
        </script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>