package com.nonbinsys.greeners.assembler;

import com.nonbinsys.greeners.controllers.ComercioController;
import com.nonbinsys.greeners.controllers.ProductoController;
import com.nonbinsys.greeners.entities.producto.Producto;
import com.nonbinsys.greeners.entities.producto.ProductoId;
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
                linkTo(methodOn(ProductoController.class).all()).withRel("comercios"));
    }

//            return EntityModel.of(comercio, //
//    linkTo(methodOn(ComercioController.class).one(comercio.getId())).withSelfRel(),
//    linkTo(methodOn(ComercioController.class).all()).withRel("comercios"));

    //producto.getId()
    @Override
    public CollectionModel<EntityModel<Producto>> toCollectionModel(Iterable<? extends Producto> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
