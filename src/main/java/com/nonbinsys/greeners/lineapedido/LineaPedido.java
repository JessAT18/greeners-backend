package com.nonbinsys.greeners.lineapedido;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import java.util.Objects;

public class LineaPedido {
    @EmbeddedId
    private LineaPedidoId id;

    @Column(name = "codPaquete")
    private String codPaquete;

    private Long cant;

    public LineaPedido() {
    }

    public LineaPedido(LineaPedidoId id, String codPaquete, Long cant) {
        this.id = id;
        this.codPaquete = codPaquete;
        this.cant = cant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineaPedido that = (LineaPedido) o;
        return Objects.equals(id, that.id) && Objects.equals(codPaquete, that.codPaquete) && Objects.equals(cant, that.cant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codPaquete, cant);
    }

    @Override
    public String toString() {
        return "LineaPedido{" +
                "id=" + id +
                ", codPaquete='" + codPaquete + '\'' +
                ", cant=" + cant +
                '}';
    }

    public LineaPedidoId getId() {
        return id;
    }

    public void setId(LineaPedidoId id) {
        this.id = id;
    }

    public String getCodPaquete() {
        return codPaquete;
    }

    public void setCodPaquete(String codPaquete) {
        this.codPaquete = codPaquete;
    }

    public Long getCant() {
        return cant;
    }

    public void setCant(Long cant) {
        this.cant = cant;
    }
}
