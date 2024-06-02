package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
}