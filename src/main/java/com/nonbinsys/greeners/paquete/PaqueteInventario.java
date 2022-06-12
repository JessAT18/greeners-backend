package com.nonbinsys.greeners.paquete;

import java.math.BigDecimal;

public class PaqueteInventario {
    private Long id;
    private String nombre;
    private String descripcion;
    private String link_paquete;

    private Boolean habilitado;

    private Long id_tipo_paquete;

    //ATRIBUTOS INVENTARIO
    private String codigo;
    private BigDecimal precio;
    private Long stock;
    private Long id_comercio;
    //id paquete viene con el paqueteDTO


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Long getId_tipo_paquete() {
        return id_tipo_paquete;
    }

    public void setId_tipo_paquete(Long id_tipo_paquete) {
        this.id_tipo_paquete = id_tipo_paquete;
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
}
