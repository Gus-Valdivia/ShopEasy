package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Rol;
import Modelo.Usuario;
import Control.UsuarioDAO;

public class RegistroServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String phoneNumber = request.getParameter("tel");
        String email = request.getParameter("email");

        Usuario nuevoUsuario = new Usuario(username, password, firstName, phoneNumber, email, Rol.CLIENTE);

        boolean registrado = UsuarioDAO.registrarUsuario(nuevoUsuario);

        if (registrado) {
            request.getSession().setAttribute("username", username);
            response.sendRedirect("SeleccionarRol.jsp");
        } else {
            System.out.println("Algo falló");
            response.sendRedirect("Error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
