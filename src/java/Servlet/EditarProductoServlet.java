package Servlet;

import Control.Conexion;
import Control.ProductoDAO;
import Modelo.Producto;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import java.sql.Connection;
import java.text.SimpleDateFormat;

@MultipartConfig(maxFileSize = 10485760)
public class EditarProductoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el parámetro del producto a agregar o eliminar
        String accion = request.getParameter("accion");
        int producto = Integer.parseInt(request.getParameter("producto"));

        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.eliminarProducto(producto);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            if (request.getParameter("fecha") != null) {

                int idProducto = Integer.parseInt(request.getParameter("idProducto"));
                float costo = Float.parseFloat(request.getParameter("costo"));
                int cantidad = Integer.parseInt(request.getParameter("cant"));
                String fecha = request.getParameter("fecha");
                String tipo = request.getParameter("tipo");
                
                ProductoDAO producto = new ProductoDAO();

                producto.editarProductoProveedor(idProducto, costo, cantidad, fecha);

                System.out.println("La fecha es: " + request.getParameter("fecha"));

                // Redireccionar a otra página después de completar el trabajo en doPost()
                response.sendRedirect("Proveedor/Inventario2.jsp?tipo="+tipo);

            } else {
                int idProducto = Integer.parseInt(request.getParameter("idProducto"));
                String nombre = request.getParameter("namep");
                int cantidad = 10;
                double prPrecio = Double.parseDouble(request.getParameter("precio"));
                String prDescripcion = request.getParameter("desc");

                Producto producto = new Producto(idProducto, nombre, cantidad, prPrecio, prDescripcion);

                Part filePart = request.getPart("imagen");
                if (filePart != null) {
                    InputStream inputStream = filePart.getInputStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    producto.setImagen(imageBytes);
                }

                ProductoDAO productoDAO = new ProductoDAO();
                productoDAO.editarProducto(producto);

                response.sendRedirect("Inventario.jsp");
            }

        } catch (Exception e) {

            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
