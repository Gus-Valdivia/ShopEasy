package Modelo;

public class Producto {
    int idProducto;
    String nombre;
    int cantidad;
    double prPrecio;
    float prCosto;
    String prDescripcion;
    byte[] prImagen;
    String Categoria_idCategoria;
    String fechaCaducidad;

    public Producto(String nombre, String fechaCaducidad) {
        this.nombre = nombre;
        this.fechaCaducidad = fechaCaducidad;
    }

    public Producto(int idProducto, String nombre, int cantidad, byte[] prImagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prImagen = prImagen;
    }
    
    
    

    public Producto(String nombre, int cantidad, double prPrecio, float prCosto, String prDescripcion, byte[] prImagen, String Categoria_idCategoria, String fechaCaducidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prPrecio = prPrecio;
        this.prCosto = prCosto;
        this.prDescripcion = prDescripcion;
        this.prImagen = prImagen;
        this.Categoria_idCategoria = Categoria_idCategoria;
        this.fechaCaducidad = fechaCaducidad;
    }

    
   
    public Producto(int idProducto, String nombre, int cantidad, double prPrecio, String prDescripcion) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prPrecio = prPrecio;
        this.prDescripcion = prDescripcion;
    }

    public Producto(int idProducto, String nombre, double prPrecio, byte[] prImagen, String categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.prPrecio = prPrecio;
        this.prImagen = prImagen;
        this.Categoria_idCategoria = categoria;
    }
    
    

    public Producto(int idProducto, String nombre, int cantidad, double prPrecio, byte[] prImagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prPrecio = prPrecio;
        this.prImagen = prImagen;
    }

    public Producto(String nombre, int cantidad, double prPrecio, String prDescripcion, String Categoria_idCategoria) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prPrecio = prPrecio;
        this.prDescripcion = prDescripcion;
        this.Categoria_idCategoria = Categoria_idCategoria;
    }

    
    
    
    public Producto(int idProducto, String nombre, int cantidad, double prPrecio, float prCosto, String prDescripcion, byte[] prImagen, String Categoria_idCategoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prPrecio = prPrecio;
        this.prCosto = prCosto;
        this.prDescripcion = prDescripcion;
        this.prImagen = prImagen;
        this.Categoria_idCategoria = Categoria_idCategoria;
    }

    
    
    public Producto(int idProducto, String nombre, int cantidad, double prPrecio, String prDescripcion, byte[] prImagen, String Categoria_idCategoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prPrecio = prPrecio;
        this.prDescripcion = prDescripcion;
        this.prImagen = prImagen;
        this.Categoria_idCategoria = Categoria_idCategoria;
    }

    
    
    public Producto(String nombre, int cantidad, double prPrecio, String prDescripcion, byte[] prImagen, String Categoria_idCategoria) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prPrecio = prPrecio;
        this.prDescripcion = prDescripcion;
        this.prImagen = prImagen;
        this.Categoria_idCategoria = Categoria_idCategoria;
    }

    
    
    public Producto(String nombre, int cantidad, double prPrecio, byte[] prImagen) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prPrecio = prPrecio;
        this.prImagen = prImagen;
    }

    public Producto(String nombre, int cantidad, double prPrecio, String prDescripcion, byte[] prImagen) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prPrecio = prPrecio;
        this.prDescripcion = prDescripcion;
        this.prImagen = prImagen;
    }
    
    
    
    public Producto(int idProducto, String nombre, int cantidad, float prCosto, byte[] prImagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prCosto = prCosto;
        this.prImagen = prImagen;
    }

    public Producto(String nombre, int cantidad, float prCosto, byte[] prImagen) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prCosto = prCosto;
        this.prImagen = prImagen;
    }

    public Producto(String nombre, int cantidad, float prCosto, String prDescripcion, byte[] prImagen) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prCosto = prCosto;
        this.prDescripcion = prDescripcion;
        this.prImagen = prImagen;
    }
    public Producto(String nombre, int cantidad, float prCosto, String prDescripcion, String Categoria_idCategoria) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prCosto = prCosto;
        this.prDescripcion = prDescripcion;
        this.Categoria_idCategoria = Categoria_idCategoria;
    }
    public Producto(String nombre, int cantidad, float prCosto, String prDescripcion, byte[] prImagen, String Categoria_idCategoria) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prCosto = prCosto;
        this.prDescripcion = prDescripcion;
        this.Categoria_idCategoria = Categoria_idCategoria;
        this.prImagen = prImagen;
        
    }
    public Producto(String nombre, int cantidad, float prCosto, String prDescripcion, String Categoria_idCategoria, byte[] prImagen) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prCosto = prCosto;
        this.prDescripcion = prDescripcion;
        this.prImagen = prImagen;
        this.Categoria_idCategoria = Categoria_idCategoria;
    }
        public Producto(int idProducto, String nombre, int cantidad, float prCosto, String prDescripcion, byte[] prImagen, String Categoria_idCategoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.prCosto = prCosto;
        this.prDescripcion = prDescripcion;
        this.prImagen = prImagen;
        this.Categoria_idCategoria = Categoria_idCategoria;
    }
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return prCosto;
    }

    public void setCosto(float prCosto) {
        this.prCosto = prCosto;
    }

    public String getDescripcion() {
        return prDescripcion;
    }

    public void setDescripcion(String prDescripcion) {
        this.prDescripcion = prDescripcion;
    }

    
    
    public byte[] getImagen() {
        return prImagen;
    }

    public void setImagen(byte[] prImagen) {
        this.prImagen = prImagen;
    }

    public String getTipo() {
        return Categoria_idCategoria;
    }

    public void setTipo(String Categoria_idCategoria) {
        this.Categoria_idCategoria = Categoria_idCategoria;
    }

    public double getPrPrecio() {
        return prPrecio;
    }

    public void setPrPrecio(double prPrecio) {
        this.prPrecio = prPrecio;
    }
    
     public float getPrCosto() {
        return prCosto;
    }

    public void setPrCosto(float prCosto) {
        this.prCosto = prCosto;
    }

    public String getPrDescripcion() {
        return prDescripcion;
    }

    public void setPrDescripcion(String prDescripcion) {
        this.prDescripcion = prDescripcion;
    }

    public byte[] getPrImagen() {
        return prImagen;
    }

    public void setPrImagen(byte[] prImagen) {
        this.prImagen = prImagen;
    }

    public String getCategoria_idCategoria() {
        return Categoria_idCategoria;
    }

    public void setCategoria_idCategoria(String Categoria_idCategoria) {
        this.Categoria_idCategoria = Categoria_idCategoria;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    
    
    

    
}
