<%-- 
    Document   : Dashboard
    Created on : Feb 18, 2024, 5:43:18 PM
    Author     : ale_d
--%>

<%@page import="Modelo.ProductosVendidos"%>
<%@page import="Modelo.Carrito"%>
<%@page import="Modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Control.ProductoDAO"%>
<!DOCTYPE html>

<%
    ProductoDAO productoDAO = new ProductoDAO();
    List<Carrito> carritos = productoDAO.ultimasCompras();
    List<Producto> productosCaducar = productoDAO.productosCaducar();
    List<ProductosVendidos> productosVendidos = productoDAO.productosVendidosMes();
    List<ProductosVendidos> productosMasVendidos = productoDAO.productosMasVendidos();
%>
<html>
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

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawCharts);

            function drawCharts() {
                // Datos para el gráfico de líneas
                var dataLine = google.visualization.arrayToDataTable([
                    ['Mes', 'Ventas'],
            <% for (ProductosVendidos producto : productosVendidos) {%>
                    ['<%= producto.getPrNombre()%>', <%= producto.getPrVendidos()%>],
            <% } %>
                ]);

                // Opciones para el gráfico de líneas
                var optionsLine = {
                    title: 'Ventas Del Mes',
                    curveType: 'function',
                    legend: {position: 'bottom'}
                };


                // Datos para el gráfico de pastel
                var dataPie = google.visualization.arrayToDataTable([
                    ['Categoría', 'Valor'],
            <% for (ProductosVendidos producto : productosMasVendidos) {%>
                    ['<%= producto.getPrNombre()%>', <%= producto.getPrVendidos()%>],
            <% } %>
                ]);

                // Opciones para el gráfico de pastel
                var optionsPie = {
                    title: 'Productos Más Vendidos'
                };

                // Dibujar los gráficos

                //Grafica De Linea
                var chartLine = new google.visualization.LineChart(document.getElementById('chart_div_line'));
                chartLine.draw(dataLine, optionsLine);

                //Grafica de Pastel
                var chartPie = new google.visualization.PieChart(document.getElementById('chart_div_pie'));
                chartPie.draw(dataPie, optionsPie);

            }
        </script>
    </head>
    <body style="background-color: #ffd155">
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

        <section class="DashboardSec">

            <div class="SeccionDashboard">
                <!-- Div para el gráfico de líneas -->
                <div class="apartadoDash" id="chart_div_line"></div>

                <!-- Tabla -->
                <div class="apartadoDash TablaDash">
                    <h2>Ultimas Compras</h2>
                    <div class="tabla-container">
                        <table id="ultimas-compras-table">
                            <thead>
                                <tr>
                                    <th>ID Del Carrito</th>
                                    <th>Fecha</th>
                                    <th>Total $:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <% for (Carrito carrito : carritos) {%>
                                <tr>
                                    <td><%= carrito.getIdCarrito()%></td>
                                    <td><%= carrito.getFecha()%></td>
                                    <td><%= carrito.getTotal()%></td>
                                </tr>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="SeccionDashboard">
                <!-- Div para el gráfico de pastel -->
                <div class="apartadoDash" id="chart_div_pie"></div>

                <!-- Tabla -->
                <div class="apartadoDash TablaDash">
                    <h2>Productos Proximos A Caducar</h2>
                    <div class="tabla-container">
                        <table id="ultimas-compras-table">
                            <thead>
                                <tr>
                                    <th>Fecha De Caducidad</th>
                                    <th>Nombre</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%for (Producto producto : productosCaducar) {%>
                                <tr>
                                    <td><%= producto.getFechaCaducidad()%></td>
                                    <td><%= producto.getNombre()%></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
