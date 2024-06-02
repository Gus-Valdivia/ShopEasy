
package Servlet;

import Control.Conexion;
import Control.ProductoDAO;
import Modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ObtenerProductosCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String tipoProducto = request.getParameter("Categoria_idCategoria");
    Connection con = Conexion.getConnection();
    ProductoDAO productoDAO = new ProductoDAO();
    List<Producto> productosFiltrados;

    if (tipoProducto != null && !tipoProducto.isEmpty()) {

        productosFiltrados = productoDAO.getProductosPorTipo(tipoProducto);
    } else {

        productosFiltrados = productoDAO.obtenerTodosLosProductos();
    }

    request.setAttribute("productosFiltrados", productosFiltrados);
    RequestDispatcher dispatcher = request.getRequestDispatcher("Productos.jsp");
    dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
