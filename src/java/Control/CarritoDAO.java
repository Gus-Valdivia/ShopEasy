/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author ale_d
 */
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class CarritoDAO {

    
    
    private Connection con;
    List<Producto> productos = new ArrayList<>();

    public CarritoDAO() {
        this.con = Conexion.getConnection();
    }

    public void realizarCompra(List<Producto> productos, List<Integer> productosVendidos, float total) {

        Random random = new Random();
        StringBuilder idBuilder = new StringBuilder();
        int longitud = random.nextInt(5) + 1; // Generar una longitud aleatoria entre 1 y 10

        for (int j = 0; j < longitud; j++) {
            int digito = random.nextInt(10); // Generar un dígito aleatorio entre 0 y 9
            idBuilder.append(digito);
        }

        int idCarrito = Integer.valueOf(idBuilder.toString());

        try (Connection con = Conexion.getConnection()) {

            System.out.println("Me estoy Ejecutando");
            String query = "INSERT INTO carrito (idCarrito, carFecha, Total) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            LocalDate fechaActual = LocalDate.now();

            String anno = String.valueOf(fechaActual.getYear());
            String mes = String.valueOf(fechaActual.getMonthValue());
            String dia = String.valueOf(fechaActual.getDayOfMonth());

            ps.setInt(1, idCarrito);
            ps.setString(2, anno + "-" + mes + "-" + dia);
            ps.setFloat(3, total);

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al registrar Carrito: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try (Connection con = Conexion.getConnection()) {

            // Restar la prCantidad comprada del inventario de cada producto en la base de datos
            for (int i = 0; i < productos.size(); i++) {
                
                String query = "UPDATE producto SET prCantidad = prCantidad - ? WHERE idProducto = ?";
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setInt(1, productosVendidos.get(i));
                    ps.setInt(2, productos.get(i).getIdProducto());
                    ps.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al Actualizar Los Productos: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        for (int i = 0; i < productos.size(); i++) {

            try (Connection con = Conexion.getConnection()) {
                //Crear Carrito/Venta
                String query1 = "INSERT INTO producto_has_carrito (Producto_idProducto, Producto_Categoria_idCategoria, Carrito_idCarrito, prVendidos) VALUES (?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query1);

                ps.setInt(1, productos.get(i).getIdProducto());
                System.out.println("El Producto es: " + productos.get(i));
                ps.setInt(2, Integer.parseInt(productos.get(i).getCategoria_idCategoria()));

                ps.setInt(3, idCarrito);
                ps.setInt(4, productosVendidos.get(i));

                ps.executeUpdate();

                ps.close();
            } catch (SQLException e) {
                System.out.println("Error al registrar Producto_Has_Carrito: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public List<Producto> obtenerCarrito(HttpServletRequest request) {
        List<Producto> productosCarrito = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        // Obtener la sesión del usuario
        HttpSession session = request.getSession();

        // Obtener la lista del carrito de la sesión
        List<String> carrito = (List<String>) session.getAttribute("carrito");

        // Verificar si la lista del carrito no es nula y no está vacía
        if (carrito != null && !carrito.isEmpty()) {

            //Utilizar Map para contar la frecuencia de cada elemento
            Map<Integer, Integer> conteo = new HashMap<>();

            // Crear un arreglo de ints con la misma longitud que la lista
            int[] arregloDeInts = new int[carrito.size()];

            try {
                // Convertir cada string a int y almacenarlo en el arreglo
                for (int i = 0; i < carrito.size(); i++) {
                    arregloDeInts[i] = Integer.parseInt(carrito.get(i));
                }

                for (int elemento : arregloDeInts) {
                    conteo.put(elemento, conteo.getOrDefault(elemento, 0) + 1);
                }

                // Crear un arreglo para almacenar los elementos únicos
                List<Integer> productos1 = new ArrayList<>(conteo.keySet());

                //Ordenar los elementos unicos
                Collections.sort(productos1);

                //Crear Un Arreglo para almacenar la frecuencia de cada elemento
                List<Integer> frecuencia = new ArrayList<>();
                for (int elemento : productos1) {
                    frecuencia.add(conteo.get(elemento));
                }

                try (Connection con = Conexion.getConnection()) {
                    //Crear Carrito/Venta

                    for (int idProducto : productos1) {

                        String query1 = "SELECT idProducto, prNombre,prImagen,prPrecio,Categoria_idCategoria FROM producto WHERE idProducto = ?;";
                        ps = con.prepareStatement(query1);

                        ps = con.prepareStatement(query1);
                        ps.setInt(1, idProducto);

                        rs = ps.executeQuery();

                        while (rs.next()) {
                            Producto producto = new Producto(
                                    rs.getInt("idProducto"),
                                    rs.getString("prNombre"),
                                    rs.getDouble("prPrecio"),
                                    rs.getBytes("prImagen"),
                                    rs.getString("Categoria_idCategoria")
                            );

                            productosCarrito.add(producto); // Agregar el producto a la lista
                        }

                        ps.close();
                    }

                } catch (SQLException e) {
                    System.out.println("Error al realizar la compra: " + e.getMessage());
                    e.printStackTrace();
                }

                // Imprimir los resultados
                System.out.println("Elementos únicos: " + productos1);
                System.out.println("Frecuencia: " + frecuencia);
            } catch (NumberFormatException e) {
                // Manejar la excepción si no se puede convertir una cadena a un entero
                System.err.println("Error al convertir una cadena a un entero: " + e.getMessage());
                e.printStackTrace();
                // También podrías lanzar una excepción personalizada o manejarla de otra manera según tus necesidades
            }
        } else {
            System.out.println("El Carrito está Vacio");
        }
        return productosCarrito;
    }
}
