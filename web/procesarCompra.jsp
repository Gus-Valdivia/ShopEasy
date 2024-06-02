<%-- 
    Document   : procesarCompra
    Created on : 27 nov. 2023, 13:36:59
    Author     : KONER
--%>

<%@page import="Control.ProductoDAO"%>
<%@page import="Modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String productoId = request.getParameter("id");
String cantidadStr = request.getParameter("cantidad");
String compraex = "";
if (productoId != null && cantidadStr != null && !productoId.isEmpty() && !cantidadStr.isEmpty()) {
    int idProducto = Integer.parseInt(productoId);
    int cantidadComprada = Integer.parseInt(cantidadStr);


    ProductoDAO productoDAO = new ProductoDAO();


    Producto producto = productoDAO.obtenerProductoPorId(idProducto);

    if (producto != null) {
        int cantidadDisponible = producto.getCantidad();

        if (cantidadComprada <= cantidadDisponible) {
            
            producto.setCantidad(cantidadDisponible - cantidadComprada);
            productoDAO.actualizarCantidad(producto);

            response.sendRedirect("pagina_inicio_cliente.jsp?compraExitosa=true");
        } else {
            
            response.sendRedirect("errorCompra.jsp");
        }
    } else {
        
        response.sendRedirect("errorProductoNoEncontrado.jsp");
    }
} else {
   
    response.sendRedirect("errorParametros.jsp");
}
%>