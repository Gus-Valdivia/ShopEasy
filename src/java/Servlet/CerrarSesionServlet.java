/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CerrarSesionServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Crear una nueva cookie con el mismo nombre pero con un tiempo de vida 0 (expirada)
        Cookie sessionCookie = new Cookie("sessionId", "");
        sessionCookie.setMaxAge(0);
        response.addCookie(sessionCookie);
        
        // Redirigir al usuario a la página de inicio de sesión
        response.sendRedirect("inicio_sesion.html");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}