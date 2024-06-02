package Servlet;

import Control.Conexion;
import Control.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class RealizarCompraServlet extends HttpServlet {

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
        String categoriaStr = request.getParameter("categoria");
        String productoId = request.getParameter("id");
        String cantidadStr = request.getParameter("cantidad");
        String total1 = request.getParameter("total");

        if (productoId != null && cantidadStr != null && !productoId.isEmpty() && !cantidadStr.isEmpty()) {
            int categoria = Integer.parseInt(categoriaStr);
            int idProducto = Integer.parseInt(productoId);
            int cantidadComprada = Integer.parseInt(cantidadStr);
            float total = Float.parseFloat(total1);

            ProductoDAO productoDAO = new ProductoDAO();
            int cantidadDisponible = productoDAO.obtenerCantidadDisponiblePorId(idProducto);

            if (cantidadDisponible >= cantidadComprada) {
                try {
                    productoDAO.realizarCompra(categoria, idProducto, cantidadComprada, total);

                    response.sendRedirect("pagina_inicio_cliente.jsp?compraExitosa=true");
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("error.jsp");
                }
            } else {

                response.sendRedirect("pagina_inicio_cliente.jsp?cantidadM=true");
            }
        } else {
            response.sendRedirect("error.jsp?param=true");
        }
    }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
