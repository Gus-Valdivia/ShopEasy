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

    <body>
        <header class="hrd1">
            <header class="hdr">
                <div class="logo-container">
                    <img src="./assets/Brand/logo.png" alt="Logo de la empresa" class="logo">
                    <span class="company-name">ShopEasy</span>
                </div>
                <nav class="nav-links">
                    <a href="inicio_sesion.html" class="button button-light btn_is"><i class="bi bi-person-fill"></i> Iniciar Sesión</a>
                    <a href="Registro.html" class="button button-dark btn_reg"><i class="bi bi-person-fill-add"></i>Registrarse</a>
                </nav>
            </header>
        </header>
        <div id="carouselExampleIndicators" class="sectionCarrousel carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item active sectionCarrousel">
                    <img class="d-block w-100" src="./assets/carrousel/easyShop(3).png" alt="First slide">
                </div>
                <div class="carousel-item sectionCarrousel">
                    <img class="d-block w-100" src="./assets/carrousel/easyShop(2).png" alt="Second slide">
                </div>
                <div class="carousel-item sectionCarrousel">
                    <img class="d-block w-100" src="./assets/carrousel/easyShop.png" alt="Third slide">
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <section class="section-container">
            <section class="section_pres" id="yellow1"></section>
            <div class="TituloPrincipal">
                <span class="TituloSHOP">¡Bienvenido a ShopEasy!</span>
            </div>
            <div class="Comentario">
                <span class="desc_neg">¡Te damos la bienvenida a ShopEasy, tu aliado en la gestión eficiente del inventario
                    para potenciar el éxito de tu tienda o local! Nuestro compromiso es minimizar tus pérdidas y contribuir
                    al crecimiento sostenible de tu negocio. Con un equipo especializado, nos dedicamos a resolver las
                    problemáticas específicas de tu establecimiento, además de brindarte el apoyo necesario para expandir tu
                    presencia en el mundo digital. ¡Descubre cómo podemos hacer que tu negocio alcance nuevos
                    horizontes!</span>
                <img src="./assets/Productos/Productos.png" alt="">
            </div>
        </section>
        <section class="sectionMarcas">
            <ul class="marcas1">
                <li><i class="fa-brands fa-facebook"></i></li>
                <li><i class="fa-brands fa-tiktok"></i></li>
                <li><i class="fa-brands fa-wix"></i></li>
                <li><i class="fa-brands fa-playstation"></i></li>
                <li><i class="fa-brands fa-reddit"></i></li>
                <li><i class="fa-brands fa-studiovinari"></i></li>
                <li><i class="fa-brands fa-cc-amazon-pay"></i></li>
            </ul>
        </section>
        <section class="section-container">
            <section class="section_pres" id="yellow2"></section>
            <div class="TituloPrincipal">
                <span class="TituloSHOP">Empieza Ahora</span>
            </div>
            <div class="Comentario">
                <img src="./assets/Productos/Productos.png" alt="">
                <span class="desc_neg">¡Te damos la bienvenida a ShopEasy, tu aliado en la gestión eficiente del inventario
                    para potenciar el éxito de tu tienda o local! Nuestro compromiso es minimizar tus pérdidas y contribuir
                    al crecimiento sostenible de tu negocio. Con un equipo especializado, nos dedicamos a resolver las
                    problemáticas específicas de tu establecimiento, además de brindarte el apoyo necesario para expandir tu
                    presencia en el mundo digital. ¡Descubre cómo podemos hacer que tu negocio alcance nuevos
                    horizontes!</span>
                <span class="TituloSHOP"><a href="">Inicia Aquí</a></span>
            </div>
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
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="JS/bubble.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>