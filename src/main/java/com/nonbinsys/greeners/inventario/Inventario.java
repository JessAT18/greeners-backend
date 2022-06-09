package com.nonbinsys.greeners.inventario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "paquetes")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private BigDecimal precio;
    private Long stock;

    private Long id_comercio;
    private Long id_descripcion_paquete;

    public Inventario() {
    }

    public Inventario(Long id, String codigo, BigDecimal precio, Long stock, Long id_comercio, Long id_descripcion_paquete) {
        this.id = id;
        this.codigo = codigo;
        this.precio = precio;
        this.stock = stock;
        this.id_comercio = id_comercio;
        this.id_descripcion_paquete = id_descripcion_paquete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getId_comercio() {
        return id_comercio;
    }

    public void setId_comercio(Long id_comercio) {
        this.id_comercio = id_comercio;
    }

    public Long getId_descripcion_paquete() {
        return id_descripcion_paquete;
    }

    public void setId_descripcion_paquete(Long id_descripcion_paquete) {
        this.id_descripcion_paquete = id_descripcion_paquete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventario inventario = (Inventario) o;
        return Objects.equals(id, inventario.id) && Objects.equals(codigo, inventario.codigo) && Objects.equals(precio, inventario.precio) && Objects.equals(stock, inventario.stock) && Objects.equals(id_comercio, inventario.id_comercio) && Objects.equals(id_descripcion_paquete, inventario.id_descripcion_paquete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, precio, stock, id_comercio, id_descripcion_paquete);
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", id_comercio=" + id_comercio +
                ", id_descripcion_paquete=" + id_descripcion_paquete +
                '}';
    }
}
