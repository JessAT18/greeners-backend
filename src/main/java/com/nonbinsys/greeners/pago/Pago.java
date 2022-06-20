package com.nonbinsys.greeners.pago;

import javax.persistence.*;

@Entity
@Table(name = "pagos")
public class Pago {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;
    @Column(name = "id_estado")
    private Long idEstado;
    @Column(name = "id_metodo_pago")
    private Long idMetodoPago;
}
