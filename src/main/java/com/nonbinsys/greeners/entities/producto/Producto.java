package com.nonbinsys.greeners.entities.producto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "productos")
public class Producto {

    @EmbeddedId
    private ProductoId productoId; //idComercio y codigo
    private String nombre;
    private String descripcion;

    public Producto() {
    }

    public Producto(ProductoId productoId, String nombre, String descripcion) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public ProductoId getProductoId() {
        return productoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(productoId, producto.productoId) && Objects.equals(nombre, producto.nombre) && Objects.equals(descripcion, producto.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productoId, nombre, descripcion);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "productoId=" + productoId +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    //region Getters and Setters
    public void setProductoId(ProductoId productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    //endregion
}
