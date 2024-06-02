/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Control.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcesarRespuestaProveedorServlet extends HttpServlet {

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
        String accion = request.getParameter("accion");
        String nombreProducto = request.getParameter("nombreProducto");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        if ("aceptar".equals(accion)) {

            ProductoDAO productoDAO = new ProductoDAO();
            productoDAO.actualizarCantidadProducto(nombreProducto, cantidad);

            response.sendRedirect("Inventario.jsp?");
        } else if ("rechazar".equals(accion)) {
            request.setAttribute("mensaje", "La solicitud fue rechazada por el proveedor.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("listaPedido.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
