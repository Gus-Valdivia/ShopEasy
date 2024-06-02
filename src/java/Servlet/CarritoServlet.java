
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la sesión del usuario
        HttpSession session = request.getSession();

        // Obtener o crear el carrito de la sesión
        List<String> carrito = (List<String>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }

        // Obtener el parámetro del producto a agregar o eliminar
        String accion = request.getParameter("accion");
        String producto = request.getParameter("producto");

        if (accion.equals("BorrarCarrito")) {
            //Eliminar Todo El Carrito
            carrito.clear();
        }else if (accion != null && producto != null && !producto.isEmpty()) {
            // Realizar la acción según la solicitud del usuario
            if (accion.equals("agregar")) {
                // Agregar el producto al carrito
                carrito.add(producto);
            } else if (accion.equals("eliminar")) {
                // Eliminar el producto del carrito
                carrito.remove(producto);
            }
        }

        // Redirigir al usuario de vuelta a la página principal o a donde sea necesario
        //response.sendRedirect("pagina_principal.jsp");
        System.out.println("El Carrito Es: " + carrito);
    }

}
