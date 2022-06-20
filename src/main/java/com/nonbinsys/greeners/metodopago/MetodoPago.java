package com.nonbinsys.greeners.metodopago;

import javax.persistence.*;

@Entity
@Table(name = "metodos_de_pago")
public class MetodoPago {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
}
