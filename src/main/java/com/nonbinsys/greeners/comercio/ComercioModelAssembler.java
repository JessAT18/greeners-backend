package com.nonbinsys.greeners.comercio;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ComercioModelAssembler implements RepresentationModelAssembler<Comercio, EntityModel<Comercio>> {
    @Override
    public EntityModel<Comercio> toModel(Comercio comercio) {
        return EntityModel.of(comercio, //
                linkTo(methodOn(ComercioController.class).unComercio(comercio.getId())).withSelfRel(),
                linkTo(methodOn(ComercioController.class).listarComercios()).withRel("comercios"));
    }

    @Override
    public CollectionModel<EntityModel<Comercio>> toCollectionModel(Iterable<? extends Comercio> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
