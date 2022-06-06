package com.nonbinsys.greeners.assembler;

import com.nonbinsys.greeners.controllers.ProductoController;
import com.nonbinsys.greeners.entities.producto.Producto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto, //
                linkTo(methodOn(ProductoController.class).one(producto.getId_comercio(), producto.getCodigo())).withSelfRel(),
                linkTo(methodOn(ProductoController.class).all()).withRel("productos"));
    }

    @Override
    public CollectionModel<EntityModel<Producto>> toCollectionModel(Iterable<? extends Producto> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
