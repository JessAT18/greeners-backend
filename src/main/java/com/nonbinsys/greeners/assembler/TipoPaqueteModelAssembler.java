package com.nonbinsys.greeners.assembler;

import com.nonbinsys.greeners.controllers.TipoPaqueteController;
import com.nonbinsys.greeners.entities.parametros.TipoPaquete;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TipoPaqueteModelAssembler implements RepresentationModelAssembler<TipoPaquete, EntityModel<TipoPaquete>> {
    @Override
    public EntityModel<TipoPaquete> toModel(TipoPaquete tipoPaquete) {
        return EntityModel.of(tipoPaquete, //
                linkTo(methodOn(TipoPaqueteController.class).one(tipoPaquete.getId())).withSelfRel(),
                linkTo(methodOn(TipoPaqueteController.class).all()).withRel("tipospaquete"));
    }

    @Override
    public CollectionModel<EntityModel<TipoPaquete>> toCollectionModel(Iterable<? extends TipoPaquete> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
