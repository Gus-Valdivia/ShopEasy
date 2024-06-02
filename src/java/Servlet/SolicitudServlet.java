package Servlet;

import Control.ProductoDAO;
import Modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SolicitudServlet extends HttpServlet {

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
    int idProducto = Integer.parseInt(request.getParameter("producto1"));
    int cantidad = Integer.parseInt(request.getParameter("cantidad1"));

    ProductoDAO productoDAO = new ProductoDAO();
    Producto producto = productoDAO.obtenerProductoPorId(idProducto);

    if (producto != null) {
        String nombreProducto = producto.getNombre();
        request.setAttribute("nombreProducto", nombreProducto);
        request.setAttribute("cantidad", cantidad);

        RequestDispatcher dispatcher = request.getRequestDispatcher("ListaPedido.jsp");
        dispatcher.forward(request, response);
    } else {
        response.sendRedirect("ListaProductos.jsp?NexisteP=true");
    }
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
