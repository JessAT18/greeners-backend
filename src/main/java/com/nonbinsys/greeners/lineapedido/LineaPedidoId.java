package com.nonbinsys.greeners.lineapedido;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LineaPedidoId implements Serializable {
    @Column(name = "id_pedido")
    private Long idPedido;
    @Column(name = "id_inventario")
    private Long idInventario;

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
    }
}
