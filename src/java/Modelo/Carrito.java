package Modelo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Carrito {

    List<Producto> productos = new ArrayList<>();
    String idCarrito;
    String fecha;
    Date carritoFecha = new Date();
    double total;

    public String getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(String idCarrito) {
        this.idCarrito = idCarrito;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    
    
    public Carrito(String idCarrito, String fecha, double total) {
        this.idCarrito = idCarrito;
        this.fecha = fecha;
        this.total = total;
    }
    
    

    public Carrito(double total) {
        this.total = total;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Date getCarritoFecha() {
        return carritoFecha;
    }

    public void setCarritoFecha(Date carritoFecha) {
        this.carritoFecha = carritoFecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    

    public void agregarAlCarrito(Producto producto) {
        productos.add(producto);
    }

}
