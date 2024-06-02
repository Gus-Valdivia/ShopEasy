package Servlet;

import Control.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InicioSesionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String id = usuarioDAO.obtenerIDRol(username, password);
        System.out.println("El ID es: " + id);

        boolean credencialesValidas = usuarioDAO.validarCredenciales(username, password);
        System.out.println("Las Credenciales son: " + credencialesValidas);

        if (credencialesValidas && id.equals("6")) {
            Cookie sessionCookie = new Cookie("sessionId", username);
            sessionCookie.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(sessionCookie);

            response.sendRedirect("pagina_inicio_cliente.jsp");
        } else if (credencialesValidas && id.equals("7")) {

            Cookie sessionCookie = new Cookie("sessionId", username);
            sessionCookie.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(sessionCookie);
            response.sendRedirect("Inventario.jsp");

        } else if (credencialesValidas && id.equals("8")) {
            Cookie sessionCookie = new Cookie("sessionId", username);
            sessionCookie.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(sessionCookie);

            switch (username) {
                case "Agua":
                    response.sendRedirect("Proveedor/Inventario2.jsp?tipo=1");
                    break;
                case "Despensa":
                    response.sendRedirect("Proveedor/Inventario2.jsp?tipo=2");
                    break;
                case "Dulces":
                    response.sendRedirect("Proveedor/Inventario2.jsp?tipo=3");
                    break;
                case "Higiene":
                    response.sendRedirect("Proveedor/Inventario2.jsp?tipo=4");
                    break;
                case "Panes":
                    response.sendRedirect("Proveedor/Inventario2.jsp?tipo=5");
                    break;
                case "Refrescos":
                    response.sendRedirect("Proveedor/Inventario2.jsp?tipo=6");
                    break;
            }
        } else {
            response.sendRedirect("inicio_sesion.html?error=invalid");
        }
    }

    private String generarTokenUnico() {

        return UUID.randomUUID().toString();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
