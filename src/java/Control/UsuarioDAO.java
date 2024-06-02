package Control;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UsuarioDAO {
    
    public static boolean registrarUsuario(Usuario usuario) {
    Connection con = null;
    PreparedStatement ps = null;

    try {
        con = Conexion.getConnection();

        if (con == null) {
            System.out.println("Error: No se pudo obtener la conexión a la base de datos.");
            return false;
        }


            String sql = "INSERT INTO usuario (username, password, firstName, phoneNumber, email) VALUES (?, ?, ?, ?, ? )";
            System.out.println("SQL query: " + sql); 

            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getFirstName());
            ps.setString(4, usuario.getPhoneNumber());
            ps.setString(5, usuario.getEmail());

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        } finally {
            // Cierra las conexiones
            Conexion.close(ps);
            Conexion.close(con);
        }
    }
    
    public static boolean actualizarRol(String username, String rolNombre) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null; // Declaración sin asignación

        try {
            con = Conexion.getConnection();

            if (con == null) {
                System.out.println("Error: No se pudo obtener la conexión a la base de datos.");
                return false;
            }

            // Obtener el id del rol basado en el nombre del rol
            String obtenerIdRolQuery = "SELECT idRol FROM rol WHERE nombre = ?";
            ps = con.prepareStatement(obtenerIdRolQuery);
            ps.setString(1, rolNombre);
            rs = ps.executeQuery();

            int idRol = 0;
            if (rs.next()) {
                idRol = rs.getInt("idRol");
            } else {
                // Si el nombre del rol no existe en la tabla Rol
                System.out.println("El rol no existe en la base de datos.");
                return false;
            }

            
            // Actualizar el rol del usuario
            String actualizarRolQuery = "UPDATE Usuario SET Rol_idRol = ? WHERE username = ?";
            ps = con.prepareStatement(actualizarRolQuery);
            ps.setInt(1, idRol);
            ps.setString(2, username);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el rol: " + e.getMessage());
            return false;
        } finally {
            // Cierra las conexiones
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(con);
        }
    }
    public String obtenerIDRol(String username, String password){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String idRol = "";
        
        try {
            con = Conexion.getConnection();
            if (con != null) {
                String query = "SELECT Rol_idRol FROM Usuario WHERE username = ? AND password = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (rs.next()) {
                    // Si se encuentra una coincidencia en la base de datos, las credenciales son válidas
                    idRol = rs.getString("Rol_idRol");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al Obtener El ID: " + e.getMessage());
        } finally {
            // Cerrar conexiones y recursos
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(con);
        }
        
        return idRol;
    }
    
    public boolean validarCredenciales(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isValid = false;

        try {
            con = Conexion.getConnection();
            if (con != null) {
                String query = "SELECT * FROM Usuario WHERE username = ? AND password = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (rs.next()) {
                    // Si se encuentra una coincidencia en la base de datos, las credenciales son válidas
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al validar las credenciales: " + e.getMessage());
        } finally {
            // Cerrar conexiones y recursos
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(con);
        }

        return isValid;
    }
    
        
}