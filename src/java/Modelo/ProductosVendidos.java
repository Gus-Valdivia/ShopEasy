/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ale_d
 */
public class ProductosVendidos {
    
    String prNombre;
    int prVendidos;
    String fecha;

    public ProductosVendidos(String prNombre, int prVendidos, String fecha) {
        this.prNombre = prNombre;
        this.prVendidos = prVendidos;
        this.fecha = fecha;
    }

    public String getPrNombre() {
        return prNombre;
    }

    public void setPrNombre(String prNombre) {
        this.prNombre = prNombre;
    }

    public int getPrVendidos() {
        return prVendidos;
    }

    public void setPrVendidos(int prVendidos) {
        this.prVendidos = prVendidos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
