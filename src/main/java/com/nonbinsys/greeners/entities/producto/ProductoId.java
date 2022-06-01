package com.nonbinsys.greeners.entities.producto;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductoId implements Serializable {
    private String id_comercio;
    private String codigo;

    public ProductoId() {
    }

    public ProductoId(String id_comercio, String codigo) {
        this.id_comercio = id_comercio;
        this.codigo = codigo;
    }

    public String getId_comercio() {
        return id_comercio;
    }

    public void setId_comercio(String id_comercio) {
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
