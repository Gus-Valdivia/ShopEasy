<%-- 
    Document   : procesarCompra2
    Created on : Apr 2, 2024, 11:17:53 PM
    Author     : Cano's Code
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.Producto"%>
<%@page import="Modelo.Producto"%>
<%@page import="java.text.ParseException"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, org.json.simple.JSONArray, org.json.simple.JSONObject" %>
<%@page import="Control.CarritoDAO"%>
<%
    // Obtener los datos enviados desde el cliente
    String jsonString = request.getReader().lines().reduce("", ( accumulator,     actual) -> accumulator + actual);

    // Parsear la cadena JSON y convertirla en un Map
    Map<String, Object> jsonObject = new HashMap<>();

    JSONParser parser = new JSONParser();
    jsonObject = (Map<String, Object>) parser.parse(jsonString);

    // Obtener los valores del Map
    JSONArray jsonArrayCantidades = (JSONArray) jsonObject.get("cantidades");
    float total = Float.parseFloat((String) jsonObject.get("total"));

    // Procesar los datos
    ArrayList<Integer> productosVendidos = new ArrayList<>();
    for (int i = 0; i < jsonArrayCantidades.size(); i++) {
        int cantidad = Integer.parseInt((String) jsonArrayCantidades.get(i));
        productosVendidos.add(cantidad);
    }

    
    // Realizar cualquier operación adicional, como guardar los datos en una base de datos
    
    CarritoDAO carrito = new CarritoDAO();
    
    List<Producto> productos = carrito.obtenerCarrito(request);
    
    carrito.realizarCompra(productos, productosVendidos, total);
    
    
    
    // Enviar una respuesta al cliente (opcional)
    JSONObject respuesta = new JSONObject();
    respuesta.put("mensaje", "Datos procesados correctamente");
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(respuesta.toString());
%>