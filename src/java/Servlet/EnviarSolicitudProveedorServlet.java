package Servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnviarSolicitudProveedorServlet extends HttpServlet {

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
        String nombreProducto = request.getParameter("nombreProducto");
        String cantidadParam = request.getParameter("cantidad");

        if (cantidadParam != null && !cantidadParam.isEmpty()) {
            try {
                int cantidad = Integer.parseInt(cantidadParam);

                String direccionIPProveedor = "127.0.0.1";
                int puertoProveedor = 12345;

                try (Socket socket = new Socket(direccionIPProveedor, puertoProveedor);
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    // Enviar la solicitud al proveedor
                    String solicitud = nombreProducto + " " + cantidad;
                    out.println(solicitud);

                    request.setAttribute("nombreProducto", nombreProducto);
                    request.setAttribute("cantidad", cantidad);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("MensajeProveedor.jsp");
                    dispatcher.forward(request, response);

                } catch (IOException e) {
                    e.printStackTrace();
                    String errorMessage = "Error al enviar la solicitud al proveedor: " + e.getMessage();

                    request.setAttribute("mensajeError", errorMessage);

                    // Redirigir a la página de error
                    RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                    dispatcher.forward(request, response);
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                String errorMessage = "La cantidad ingresada no es válida."; 

                request.setAttribute("mensajeError", errorMessage);

                RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                dispatcher.forward(request, response);
            }
        } else {

            String errorMessage = "La cantidad no fue proporcionada.";

            request.setAttribute("mensajeError", errorMessage);

            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
