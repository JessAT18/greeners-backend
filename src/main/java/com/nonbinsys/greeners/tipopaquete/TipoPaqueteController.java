package com.nonbinsys.greeners.tipopaquete;

import com.nonbinsys.greeners.producto.ProductoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/parametros/tipospaquete")
public class TipoPaqueteController {
    @Autowired
    ITipoPaqueteService iTipoPaqueteService;
    private final TipoPaqueteModelAssembler assembler;

    public TipoPaqueteController(TipoPaqueteModelAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/listarTiposPaquete")
    public CollectionModel<EntityModel<TipoPaquete>> listarTiposPaquete() {
        List<EntityModel<TipoPaquete>> tipospaquete = iTipoPaqueteService.listarTiposPaquete().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tipospaquete, linkTo(methodOn(ProductoController.class).listarProductos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<TipoPaquete> unTipoPaquete(@PathVariable Long id) {
        TipoPaquete inventario = iTipoPaqueteService.unTipoPaquete(id)
                .orElseThrow(() -> new TipoPaqueteNotFoundException(id));

        return assembler.toModel(inventario);
    }
}
