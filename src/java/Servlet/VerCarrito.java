/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ale_d
 */
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/VerCarritoServlet")
public class VerCarrito extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la sesión del usuario
        HttpSession session = request.getSession();

        // Obtener la lista de productos en el carrito desde la sesión
        List<String> carrito = (List<String>) session.getAttribute("carrito");

        // Realizar cualquier operación que necesites con la lista de productos en el carrito
        if (carrito != null) {
            for (String producto : carrito) {
                System.out.println("Producto en el carrito: " + producto);
            }
        } else {
            System.out.println("El carrito está vacío.");
        }

        // Redirigir o mostrar la información de alguna manera
        // response.sendRedirect("pagina_carrito.jsp");
    }
}