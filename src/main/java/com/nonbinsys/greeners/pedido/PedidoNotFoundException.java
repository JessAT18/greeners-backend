package com.nonbinsys.greeners.pedido;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException(Long id) {
        super("No se encontro el pedido con id " + id);
    }
}