package com.nonbinsys.greeners.assembler.paquete;

import com.nonbinsys.greeners.controllers.paquete.DescripcionPaqueteController;
import com.nonbinsys.greeners.entities.paquete.DescripcionPaquete;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DescripcionPaqueteModelAssembler implements RepresentationModelAssembler<DescripcionPaquete, EntityModel<DescripcionPaquete>> {
    @Override
    public EntityModel<DescripcionPaquete> toModel(DescripcionPaquete descripcionPaquete) {
        return EntityModel.of(descripcionPaquete,
                linkTo(methodOn(DescripcionPaqueteController.class).one(descripcionPaquete.getId())).withSelfRel(),
                linkTo(methodOn(DescripcionPaqueteController.class).all()).withRel("descripcionpaquetes")
                );
    }

    @Override
    public CollectionModel<EntityModel<DescripcionPaquete>> toCollectionModel(Iterable<? extends DescripcionPaquete> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
