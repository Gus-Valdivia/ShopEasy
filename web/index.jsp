<%-- 
    Document   : index
    Created on : 18/02/2024
    Author     : Cano's Code
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <jsp:include page="templates/Referencias/Todas_ref.jsp"/>
        <title>ShopEasy | Compra Facil Y Ahora</title>
    </head>
    <body class="index">
        <header class="hrd1" id="index_hdr">
            <header class="hdr">
                <div class="logo-container">
                    <img src="./assets/Brand/logo.png" alt="Logo de la empresa" class="logo">
                    <span class="company-name">ShopEasy</span>
                </div>
                <nav class="nav-links">
                    <a href="inicio_sesion.html" class="btn_is"><i class="bi bi-person-fill"></i> Iniciar Sesión</a>
                    <a href="Registro.html" class="btn_reg"><i class="bi bi-person-fill-add"></i> Registrarse</a>
                </nav>
            </header>
        </header>
        <section class="section-container">
            <span class="bnv_es">Se Bienvenido a <span>ShopEasy</span></span>
            <div class="TituloPrincipal">
                <span class="TituloSHOP">¡Impulsa tu negocio, optimiza tu inventario!</span>
            </div>
            <div class="Comentario">
                <span class="desc_neg">¡Te damos la bienvenida a ShopEasy, tu aliado en la gestión eficiente del inventario
                    para potenciar el éxito de tu tienda o local! Nuestro compromiso es minimizar tus pérdidas y contribuir
                    al crecimiento sostenible de tu negocio. Con un equipo especializado, nos dedicamos a resolver las
                    problemáticas específicas de tu establecimiento, además de brindarte el apoyo necesario para expandir tu
                    presencia en el mundo digital. ¡Descubre cómo podemos hacer que tu negocio alcance nuevos
                    horizontes!
                </span>
            </div>
            <a href="inicio_sesion.html"><button>Comienza <i class="bi bi-arrow-right-circle-fill"></i></button></a>
        </section>
        <div class="footer-bottom">
            <span>&copy; 2024 Easy Shop. Todos los derechos reservados.</span>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="JS/index.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>