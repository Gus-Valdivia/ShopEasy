package Modelo;

public class Venta {
    Usuario comprador;
    Producto producto;
    int cantidad;

    public Venta(Usuario comprador, Producto producto, int cantidad) {
        this.comprador = comprador;
        this.producto = producto;
        this.cantidad = cantidad;
    }
}
