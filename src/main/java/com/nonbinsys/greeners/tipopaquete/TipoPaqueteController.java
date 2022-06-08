package com.nonbinsys.greeners.tipopaquete;

import com.nonbinsys.greeners.producto.ProductoController;
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
    private final TipoPaqueteRepository repository;
    private final TipoPaqueteModelAssembler assembler;

    public TipoPaqueteController(TipoPaqueteRepository repository, TipoPaqueteModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/listarTiposPaquete")
    public CollectionModel<EntityModel<TipoPaquete>> all() {
        List<EntityModel<TipoPaquete>> tipospaquete = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tipospaquete, linkTo(methodOn(ProductoController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<TipoPaquete> one(@PathVariable Long id) {
        TipoPaquete paquete = repository.findById(id)
                .orElseThrow(() -> new TipoPaqueteRepository.TipoPaqueteNotFoundException(id));

        return assembler.toModel(paquete);
    }
}
