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

@MultipartConfig(maxFileSize = 10485760)
public class AgregarProductoServlet extends HttpServlet {

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
           
            String nombre = request.getParameter("namep");
            System.out.println("EL nombre es: " + nombre);
            int cantidad = Integer.parseInt(request.getParameter("cant"));
            double prPrecio = Double.parseDouble(request.getParameter("precio"));
            String prDescripcion = request.getParameter("desc");
            String Categoria_idCategoria = request.getParameter("tipo");

            Producto producto = new Producto(nombre,cantidad,prPrecio,prDescripcion,Categoria_idCategoria);

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
            productoDAO.agregarProducto(producto);

            response.sendRedirect("Inventario.jsp"); 
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
