package com.nonbinsys.greeners.inventario;

import java.math.BigDecimal;

public class InventarioPorComercioDTO {
    private Long id;
    private String codigo;
    private BigDecimal precio;
    private String nombre;
    private String descripcion;
    private String link_paquete;

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

    public String getLink_paquete() {
        return link_paquete;
    }

    public void setLink_paquete(String link_paquete) {
        this.link_paquete = link_paquete;
    }
}
