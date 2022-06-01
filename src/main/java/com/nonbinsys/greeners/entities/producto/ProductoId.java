package com.nonbinsys.greeners.entities.producto;

import java.io.Serializable;
import java.util.Objects;

public class ProductoId implements Serializable {
    private Long id_comercio;
    private String codigo;

    public ProductoId() {
    }

    public ProductoId(Long id_comercio, String codigo) {
        this.id_comercio = id_comercio;
        this.codigo = codigo;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoId that = (ProductoId) o;
        return Objects.equals(id_comercio, that.id_comercio) && Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_comercio, codigo);
    }
}
