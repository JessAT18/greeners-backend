package com.nonbinsys.greeners.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private IPedidoService iPedidoService;

    @GetMapping("/listarPedidos")
    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = iPedidoService.listarPedidos();

        return pedidos;
    }

    @GetMapping("/{id}")
    public Pedido unPedido(@PathVariable Long id) {
        Pedido pedido = iPedidoService.unPedido(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));

        return pedido;
    }
/*
    @PostMapping("/crearNuevoPedido")
    public Pedido nuevoPedido(@RequestBody Pedido pedido) {

    }*/
}
