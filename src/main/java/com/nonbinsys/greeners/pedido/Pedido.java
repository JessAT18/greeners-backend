package com.nonbinsys.greeners.pedido;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "id_pago")
    private Long idPago;

    public Pedido() {
    }

    public Pedido(Long id, Date fecha, Long idCliente, Long idPago) {
        this.id = id;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idPago = idPago;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(fecha, pedido.fecha) && Objects.equals(idCliente, pedido.idCliente) && Objects.equals(idPago, pedido.idPago);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, idCliente, idPago);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", idCliente=" + idCliente +
                ", idPago=" + idPago +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }
}
