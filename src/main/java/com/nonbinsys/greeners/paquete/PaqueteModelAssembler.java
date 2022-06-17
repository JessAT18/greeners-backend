package com.nonbinsys.greeners.paquete;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PaqueteModelAssembler implements RepresentationModelAssembler<Paquete, EntityModel<Paquete>> {
    @Override
    public EntityModel<Paquete> toModel(Paquete paquete) {
        return EntityModel.of(paquete,
                linkTo(methodOn(PaqueteController.class).unPaquete(paquete.getId())).withSelfRel(),
                linkTo(methodOn(PaqueteController.class).listarPaquetes()).withRel("paquetes")
                );
    }

    @Override
    public CollectionModel<EntityModel<Paquete>> toCollectionModel(Iterable<? extends Paquete> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
