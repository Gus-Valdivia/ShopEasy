/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Control.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KONER
 */
public class CompletarRegistroServlet extends HttpServlet {

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
        String rolSeleccionado = request.getParameter("rol");

        String username = (String) request.getSession().getAttribute("username");

        boolean rolActualizado = UsuarioDAO.actualizarRol(username, rolSeleccionado);

        if (rolActualizado) {
            response.sendRedirect("inicio_sesion.html");
        } else {
            response.sendRedirect("Error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
