package com.nonbinsys.greeners.pedido;

import java.util.List;
import java.util.Optional;

public interface IPedidoService {
    List<Pedido> listarPedidos();
    Optional<Pedido> unPedido(Long id);
    Pedido guardarPedido(Pedido pedido);
    Boolean eliminarPedido(Long id);

}
