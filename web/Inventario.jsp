<%-- 
    Document   : Inventario
    Created on : 26 feb. 2024
    Author     : Cano's Code
--%>

<%@page import="java.util.Base64"%>
<%@page import="Control.ProductoDAO"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Producto"%>
<%@page import="Modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ProductoDAO productoDAO = new ProductoDAO();
    List<Producto> todosLosProductos = productoDAO.obtenerTodosLosProductosST();
    List<Producto> productosMenosCantidad = productoDAO.productosPocaCantidad();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="./CSS/css.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/002144bd49.js" crossorigin="anonymous"></script>
        <title>ShopEasy | Compra Facil Y Ahora</title>
    </head>
    <body>
        <script>
            function search_product() {
                let input = document.getElementById('inputbuscar').value
                input = input.toLowerCase();
                let x = document.getElementsByClassName('productCard3');
                for (i = 0; i < x.length; i++) {
                    if (!x[i].innerHTML.toLowerCase().includes(input)) {
                        x[i].style.display = "none";
                    } else {
                        x[i].style.display = "flex";
                    }
                }
            }
        </script>
        <header class="hdr">
            <div class="logo-container">
                <img src="./assets/Brand/logo.png" alt="Logo de la empresa" class="logo">
                <span class="company-name">ShopEasy</span>
            </div>
            <nav class="nav-links">
                <a href="Inventario.jsp" class="button button-light">Inventario</a>
                <a href="Dashboard.jsp" class="button button-light">Dashboard</a>
                <a href="RegistroProducto.jsp" class="button button-dark">Agregar Producto</a>
                <a href="index.jsp" class="button button-dark">Cerrar Sesión</a>
            </nav>
        </header>

        <section class="ProductosDisplay" id="yellow1">
            <div class="buscador">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" id="inputbuscar" placeholder="Buscar productos... " onkeyup="search_product()">
            </div>
            <% for (Producto producto : todosLosProductos) {%>
            <div class="productCard3" onclick="editarProducto(<%= producto.getIdProducto()%>)">
                <% byte[] imageData = producto.getImagen();
                    String base64Image = imageData != null
                            ? java.util.Base64.getEncoder().encodeToString(imageData) : "";%>
                <img src="data:image/jpeg;base64, <%= base64Image%>"
                     alt="<%= producto.getNombre()%>" style="width: 100px; height: 100px;">
                <h3><%= producto.getNombre()%></h3>
                <p>$ <span><%=producto.getPrPrecio()%></span></p>
                <p>Quedan: <span><%=producto.getCantidad()%></span></p>
            </div>
            <% }%>

        </section>
        <div id="alerts-container" class="alerts-fixed"></div>
        <script>

            function editarProducto(idProducto) {
                window.location.href = 'EditarProducto.jsp?productoId=' + idProducto;
            }
        </script>

        <script src="JS/alertas.js"></script>
        <%
            for (Producto producto : productosMenosCantidad) {
        %>
        <script>
            mostrarAlerta({
                nombre: "<%= producto.getNombre()%>",
                imagen: "data:image/jpeg;base64, <%= Base64.getEncoder().encodeToString(producto.getImagen())%>",
                cantidad: <%= producto.getCantidad()%>
            });
        </script>
        <% }%>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- <script src="JS/bubble.js"></script> -->
    </body>
</html>