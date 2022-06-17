package com.nonbinsys.greeners.inventario;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {

    @Override
    public EntityModel<Inventario> toModel(Inventario inventario) {
        return EntityModel.of(inventario,
                linkTo(methodOn(InventarioController.class).unInventario(inventario.getId())).withSelfRel(),
                linkTo(methodOn(InventarioController.class).listarInventarios()).withRel("inventarios"));
    }

    @Override
    public CollectionModel<EntityModel<Inventario>> toCollectionModel(Iterable<? extends Inventario> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
