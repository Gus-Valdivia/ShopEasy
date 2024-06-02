package Control;

import Modelo.Producto;
import Modelo.Carrito;
import Modelo.ProductosVendidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductoDAO {

    private Connection con;
    private byte[] prImagen;

    public ProductoDAO() {
        this.con = Conexion.getConnection();
    }

    public void agregarProducto(Producto producto) {
        try {

            String query = "INSERT INTO producto (prNombre, prCantidad, prPrecio, prDescripcion, Categoria_idCategoria, prImagen) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getCantidad());
            ps.setDouble(3, producto.getPrPrecio());
            ps.setString(4, producto.getDescripcion());
            ps.setString(5, producto.getTipo());
            ps.setBytes(6, producto.getImagen());
            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al agregar el producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminarProducto(int idProducto) {

        try {
            String query1 = "DELETE FROM producto_has_carrito WHERE (Producto_idProducto = ?)";
            String query = "DELETE FROM producto WHERE (idProducto = ?)";
            PreparedStatement ps = con.prepareStatement(query);
            PreparedStatement ps1 = con.prepareStatement(query1);

            ps1.setInt(1, idProducto);
            ps.setInt(1, idProducto);

            ps1.executeUpdate();
            ps.executeUpdate();

            ps1.close();
            ps.close();

        } catch (SQLException e) {

            System.out.println("Error al eliminar el producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void editarProducto(Producto producto) {
        try {

            String query = "UPDATE producto SET prNombre = ?, prPrecio = ?, prDescripcion = ? WHERE idProducto = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrPrecio());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getIdProducto());
            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al editar el producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void editarProductoProveedor(int idProducto, float costo, int cantidad, String fecha) {

        try {

            String query = "UPDATE producto SET prCantidad = ?, prCosto = ?, prFechaCaducidad = ? WHERE idProducto = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, cantidad);
            ps.setFloat(2, costo);
            if (fecha.isEmpty()) {

                ps.setNull(3, java.sql.Types.DATE);
                
            }else{
            ps.setString(3, fecha);
            }
            ps.setInt(4, idProducto);
            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al editar el producto por parte del proveedor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void realizarCompra(int categoria, int idProducto, int cantidadComprada, float total) {

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
        }

        try (Connection con = Conexion.getConnection()) {
            // Restar la prCantidad comprada del inventario del producto en la base de datos
            String query = "UPDATE producto SET prCantidad = prCantidad - ? WHERE idProducto = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, cantidadComprada);
                ps.setInt(2, idProducto);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar la compra: " + e.getMessage());
            e.printStackTrace();
        }

        try (Connection con = Conexion.getConnection()) {
            //Crear Carrito/Venta
            String query1 = "INSERT INTO producto_has_carrito (Producto_idProducto, Producto_Categoria_idCategoria, Carrito_idCarrito, prVendidos) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query1);

            ps.setInt(1, idProducto);
            System.out.println("El Producto es: " + idProducto);
            ps.setInt(2, categoria);

            ps.setInt(3, idCarrito);
            ps.setInt(4, cantidadComprada);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al registrar Producto_Has_Carrito: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void actualizarCantidad(Producto producto) {
        try {
            // Preparar la consulta SQL para actualizar la prCantidad del producto
            String query = "UPDATE producto SET prCantidad = ? WHERE idProducto = ?";
            PreparedStatement ps = con.prepareStatement(query);

            // Establecer los parámetros en la consulta SQL
            ps.setInt(1, producto.getCantidad());
            ps.setInt(2, producto.getIdProducto()); // Suponiendo que tienes un método getId() en la clase Producto

            // Ejecutar la actualización en la base de datos
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar la prCantidad del producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void actualizarCantidadProducto(String nombreProducto, int prCantidad) {
        PreparedStatement pstmt = null;

        try {
            // Query de actualización para sumar la prCantidad al producto existente
            String updateQuery = "UPDATE producto SET prCantidad = prCantidad + ? WHERE prNombre = ?";
            pstmt = con.prepareStatement(updateQuery);
            pstmt.setInt(1, prCantidad);
            pstmt.setString(2, nombreProducto);
            pstmt.executeUpdate();

            // Cerrar recursos
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar el error apropiadamente
        } finally {
            // Cerrar recursos
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para obtener productos filtrados por Categoria_idCategoria
    public List<Producto> getProductosPorTipo(String Categoria_idCategoria) {
        List<Producto> productosFiltrados = new ArrayList<>();
        con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConnection();

            String query = "SELECT idProducto, prNombre, prCantidad, prPrecio, prImagen FROM producto WHERE Categoria_idCategoria = ?";

            ps = con.prepareStatement(query);
            ps.setString(1, Categoria_idCategoria);

            rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("prNombre"),
                        rs.getInt("prCantidad"),
                        rs.getDouble("prPrecio"),
                        rs.getBytes("prImagen")
                );
                producto.setTipo(Categoria_idCategoria); // Establecer el Categoria_idCategoria del producto

                productosFiltrados.add(producto); // Agregar el producto a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener productos por Categoria_idCategoria: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(con);
        }

        return productosFiltrados;
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConnection();

            String query = "SELECT idProducto, prNombre, prCantidad, prPrecio, prDescripcion, prImagen FROM producto";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getString("prNombre"),
                        rs.getInt("prCantidad"),
                        rs.getDouble("prPrecio"),
                        rs.getBytes("prImagen")
                );

                producto.setTipo(rs.getString("Categoria_idCategoria")); // Establecer el Categoria_idCategoria del producto

                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los productos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(con);
        }

        return productos;
    }

    public Producto obtenerProductoPorId(int idProducto) {
        Producto producto = null;
        con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConnection();
            String query = "SELECT prNombre, prCantidad, prPrecio,prCosto, prDescripcion, prImagen, Categoria_idCategoria, prFechaCaducidad FROM producto WHERE idProducto = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();

            if (rs.next()) {
                producto = new Producto(
                        rs.getString("prNombre"),
                        rs.getInt("prCantidad"),
                        rs.getDouble("prPrecio"),
                        rs.getFloat("prCosto"),
                        rs.getString("prDescripcion"), // Asegúrate de incluir la columna prDescripcion aquí
                        rs.getBytes("prImagen"),
                        rs.getString("Categoria_idCategoria"),
                        rs.getString("prFechaCaducidad")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el producto por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(con);
        }

        return producto;
    }

    public List<Producto> obtenerInventario() {
        List<Producto> productos = new ArrayList<>();
        con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConnection();

            String query = "SELECT idProducto, prNombre, prCantidad, prPrecio, prDescripcion, prImagen, Categoria_idCategoria FROM producto";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getString("prNombre"),
                        rs.getInt("prCantidad"),
                        rs.getDouble("prPrecio"),
                        rs.getString("prDescripcion"),
                        rs.getBytes("prImagen"),
                        rs.getString("Categoria_idCategoria")
                );

                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los productos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(con);
        }

        return productos;
    }

    public List<Producto> obtenerTodosLosProductosST() {
        List<Producto> productos = new ArrayList<>();
        con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConnection();

            String query = "SELECT idProducto, prNombre, prCantidad, prPrecio, prDescripcion, prImagen, Categoria_idCategoria FROM producto";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("prNombre"),
                        rs.getInt("prCantidad"),
                        rs.getDouble("prPrecio"),
                        rs.getString("prDescripcion"),
                        rs.getBytes("prImagen"),
                        rs.getString("Categoria_idCategoria")
                );

                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los productos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(con);
        }

        return productos;
    }

    public int obtenerCantidadDisponiblePorId(int idProducto) {
        int cantidadDisponible = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConnection();

            String query = "SELECT prCantidad FROM producto WHERE idProducto = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();

            if (rs.next()) {
                cantidadDisponible = rs.getInt("prCantidad");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la prCantidad disponible del producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(con);
        }

        return cantidadDisponible;
    }

    public List<Carrito> ultimasCompras() {

        List<Carrito> carritos = new ArrayList<>();
        con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = Conexion.getConnection();

            String query = "SELECT idCarrito, carFecha, Total FROM carrito ORDER BY carFecha DESC";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                Carrito carrito = new Carrito(
                        rs.getString("idCarrito"),
                        rs.getString("carFecha"),
                        rs.getDouble("Total")
                );

                carritos.add(carrito);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los carritos: " + e.getMessage());
            e.printStackTrace();
        }

        return carritos;
    }

    public List<Producto> productosCaducar() {

        List<Producto> productos = new ArrayList<>();

        con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConnection();

            String query = "SELECT prFechaCaducidad, prNombre  FROM producto WHERE prFechaCaducidad IS NOT NULL  ORDER BY prFechaCaducidad ASC LIMIT 10";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getString("prNombre"),
                        rs.getString("prFechaCaducidad")
                );

                productos.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los productos a caducar: " + e.getMessage());
            e.printStackTrace();
        }

        return productos;
    }

    public List<ProductosVendidos> productosVendidosMes() {

        List<ProductosVendidos> productosVendidos = new ArrayList<>();
        con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = Conexion.getConnection();
            String query = "SELECT DISTINCT producto.prNombre, ventas.totalVendidos, carrito.carFecha \n"
                    + "FROM producto \n"
                    + "INNER JOIN (\n"
                    + "    SELECT Producto_idProducto, SUM(prVendidos) AS totalVendidos\n"
                    + "    FROM producto_has_carrito\n"
                    + "    GROUP BY Producto_idProducto\n"
                    + ") AS ventas ON producto.idProducto = ventas.Producto_idProducto\n"
                    + "INNER JOIN producto_has_carrito ON producto.idProducto = producto_has_carrito.Producto_idProducto \n"
                    + "INNER JOIN carrito ON producto_has_carrito.Carrito_idCarrito = carrito.idCarrito\n"
                    + "WHERE MONTH(carrito.carFecha) = ?\n"
                    + "ORDER BY ventas.totalVendidos DESC\n"
                    + "LIMIT 10;";

            ps = con.prepareStatement(query);

            // Obtener la fecha actual
            LocalDate fechaActual = LocalDate.now();

            // Obtener el mes actual
            Month mesActual = fechaActual.getMonth();

            // Obtener el número del mes actual (1 para enero, 2 para febrero, etc.)
            int numeroMesActual = mesActual.getValue();

            // Imprimir el número del mes actual
            System.out.println("El número del mes actual es: " + numeroMesActual);

            ps.setInt(1, numeroMesActual);

            rs = ps.executeQuery();

            while (rs.next()) {
                ProductosVendidos productosVendidos2 = new ProductosVendidos(
                        rs.getString("producto.prNombre"),
                        rs.getInt("ventas.totalVendidos"),
                        rs.getString("carrito.carFecha")
                );

                productosVendidos.add(productosVendidos2);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los carritos: " + e.getMessage());
            e.printStackTrace();
        }

        return productosVendidos;

    }

    public List<ProductosVendidos> productosMasVendidos() {
        List<ProductosVendidos> productosVendidos = new ArrayList<>();
        con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = Conexion.getConnection();
            String query = "SELECT DISTINCT producto.prNombre, ventas.totalVendidos, carrito.carFecha \n"
                    + "FROM producto \n"
                    + "INNER JOIN (\n"
                    + "    SELECT Producto_idProducto, SUM(prVendidos) AS totalVendidos\n"
                    + "    FROM producto_has_carrito\n"
                    + "    GROUP BY Producto_idProducto\n"
                    + ") AS ventas ON producto.idProducto = ventas.Producto_idProducto\n"
                    + "INNER JOIN producto_has_carrito ON producto.idProducto = producto_has_carrito.Producto_idProducto \n"
                    + "INNER JOIN carrito ON producto_has_carrito.Carrito_idCarrito = carrito.idCarrito \n"
                    + "ORDER BY ventas.totalVendidos DESC  LIMIT 4";

            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                ProductosVendidos productosVendidos2 = new ProductosVendidos(
                        rs.getString("producto.prNombre"),
                        rs.getInt("ventas.totalVendidos"),
                        rs.getString("carrito.carFecha")
                );

                productosVendidos.add(productosVendidos2);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los carritos: " + e.getMessage());
            e.printStackTrace();
        }

        return productosVendidos;
    }

    public List<Producto> productosPocaCantidad() {

        List<Producto> productosPocaCantidad = new ArrayList<>();
        con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConnection();

            String query = "SELECT idProducto, prNombre, prCantidad, prImagen FROM producto WHERE prCantidad <= 5";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("prNombre"),
                        rs.getInt("prCantidad"),
                        rs.getBytes("prImagen")
                );

                productosPocaCantidad.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los productos A punto de acabarse: " + e.getMessage());
            e.printStackTrace();
        }

        return productosPocaCantidad;
    }
}
