package com.nonbinsys.greeners.producto.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "productos")
@IdClass(ProductoId.class)
public class Producto {

    @Id
    private Long id_comercio;
    @Id
    private String codigo;
    private String nombre;
    private String descripcion;

    public Producto() {
    }

    public Producto(Long id_comercio, String codigo, String nombre, String descripcion) {
        this.id_comercio = id_comercio;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(id_comercio, producto.id_comercio) && Objects.equals(codigo, producto.codigo) && Objects.equals(nombre, producto.nombre) && Objects.equals(descripcion, producto.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_comercio, codigo, nombre, descripcion);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id_comercio='" + id_comercio + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    //region Getters and Setters


    public Long getId_comercio() {
        return id_comercio;
    }

    public void setId_comercio(Long id_comercio) {
        this.id_comercio = id_comercio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
