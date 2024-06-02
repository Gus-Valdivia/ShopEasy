<%-- 
    Document   : RegistroProducto
    Created on : 18 nov. 2024,
    Author     : Cano's Code
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Producto"%>
<%@page import="Control.ProductoDAO"%>
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
    <% String productoId = request.getParameter("productoId"); %>


    <%
        int idProducto = Integer.parseInt(productoId);

        ProductoDAO productoDAO = new ProductoDAO();

        Producto producto = productoDAO.obtenerProductoPorId(idProducto);
    %>
    <body>
        <header class="hdr">
            <div class="logo-container">
                <img src="./assets/Brand/logo.png" alt="Logo de la empresa" class="logo">
                <span class="company-name">ShopEasy</span>
            </div>
            <nav class="nav-links">
                <a href="Proveedor/Inventario2.jsp?tipo=<%= producto.getCategoria_idCategoria() %>" class="button button-light"><i class="bi bi-person-fill"></i>Inventario</a>
                <a href="index.jsp" class="button button-dark">Cerrar Sesión</a>
            </nav>
        </header>

        <form action="/EasyShop/EditarProductoServlet" method="post" enctype="multipart/form-data">
            <section class="AgregarProducto">
                <div class="imgPRODUCTO">
                    <div id="imagenSeleccionada" class=" imagen-preview">
                        <img src="data:image/jpeg;base64, <%= java.util.Base64.getEncoder().encodeToString(producto.getImagen())%>" alt="<%= producto.getNombre()%>" class="imagenSeleccionada" id="imagenPrevista">
                        <span class="texto-preview">Visualización Previa</span>
                    </div>
                </div>
                <div class="formularioProducto">
                    <label for="exampleInputEmail1">Nombre</label>
                    <input class="form-control" type="text" id="namep" name="namep" placeholder="Ingresa Nombre" disabled value="<%= producto.getNombre()%>">
                    <label for="">Descripción</label>
                    <textarea class="form-control" id="" rows="3" id="desc" name="desc" disabled><%= producto.getDescripcion()%></textarea>
                    <label for="">Fecha De Caducidad</label>
                    <input type="date" id="fecha" name="fecha" value="<%= producto.getFechaCaducidad()%>">
                    <label for="">Costo</label>
                    <input class="form-control" step="any" type="number" class="form-control" id="costo" name="costo" value="<%= producto.getCosto()%>">
                    <label for="">Precio</label>
                    <input class="form-control" step="any" type="number" class="form-control" id="precio" name="precio" disabled value="<%= producto.getPrPrecio()%>">
                    <label for="">Cantidad</label>
                    <input maxlength="2" class="form-control" type="number" class="form-control" id="cant" name="cant" value="<%= producto.getCantidad()%>" oninput="limitarValor()">
                    <input hidden value="<%=idProducto%>" id="idProducto" name="idProducto">
                    <input hidden value="<%= producto.getTipo()%>" name="tipo" id="tipo">
                    <input type="submit" value="Rellenar" class="button button-dark">
                </div>
                <script>
                    function limitarValor() {
                        var valorIngresado = document.getElementById("inputCampo").value;
                        var valorNumerico = parseInt(valorIngresado);

                        if (isNaN(valorNumerico) || valorNumerico > 50) {
                            document.getElementById("inputCampo").value = ""; // Limpiar el campo si el valor no es válido
                            alert("Por favor ingrese un valor numérico entre 0 y 50.");
                        }
                    }
                </script>
                <script>
                    const inputImagen = document.getElementById('imagen');
                    const imagenPrevista = document.getElementById('imagenPrevista');
                    const textoPreview = document.querySelector('.texto-preview');

                    inputImagen.addEventListener('change', function () {
                        const file = this.files[0];
                        if (file) {
                            const reader = new FileReader();
                            textoPreview.style.display = 'none';
                            imagenPrevista.style.display = 'block';
                            reader.addEventListener('load', function () {
                                imagenPrevista.setAttribute('src', this.result);
                            });
                            reader.readAsDataURL(file);
                        } else {
                            textoPreview.style.display = null;
                            imagenPrevista.style.display = null;
                            imagenPrevista.setAttribute('src', '#');
                        }
                    });

                    // Arrastrar y soltar
                    imagenPrevista.addEventListener('dragover', function (e) {
                        e.preventDefault();
                        this.classList.add('dragover');
                    });

                    imagenPrevista.addEventListener('dragleave', function () {
                        this.classList.remove('dragover');
                    });

                    imagenPrevista.addEventListener('drop', function (e) {
                        e.preventDefault();
                        this.classList.remove('dragover');
                        const file = e.dataTransfer.files[0];
                        if (file) {
                            const reader = new FileReader();
                            textoPreview.style.display = 'none';
                            imagenPrevista.style.display = 'block';
                            reader.addEventListener('load', function () {
                                imagenPrevista.setAttribute('src', this.result);
                            });
                            reader.readAsDataURL(file);
                        }
                    });
                </script>
                <script src="JS/bubble.js"></script>

        </form>
    </section>
</body>

</html>