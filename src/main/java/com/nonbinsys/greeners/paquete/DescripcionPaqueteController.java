package com.nonbinsys.greeners.paquete;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/paquetes/descripcionpaquete")
public class DescripcionPaqueteController {
    private final DescripcionPaqueteRepository repository;
    private final DescripcionPaqueteModelAssembler assembler;

    public DescripcionPaqueteController(DescripcionPaqueteRepository repository, DescripcionPaqueteModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/listarDescripcionPaquetes")
    public CollectionModel<EntityModel<DescripcionPaquete>> all() {
        List<EntityModel<DescripcionPaquete>> descripcionesPaquetes = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(descripcionesPaquetes, linkTo(methodOn(DescripcionPaqueteController.class).all()).withSelfRel());
    }

    @PostMapping("/crearNuevoComercio")
    ResponseEntity<?> nuevaDescripcionPaquete (@RequestBody DescripcionPaquete nuevoComercio) {
        EntityModel<DescripcionPaquete> entityModel = assembler.toModel(repository.save(nuevoComercio));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<DescripcionPaquete> one(@PathVariable Long id) {
        DescripcionPaquete descripcionPaquete = repository.findById(id)
                .orElseThrow(() -> new DescripcionPaqueteNotFoundException(id));

        return assembler.toModel(descripcionPaquete);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceDescripcionPaquete(@RequestBody DescripcionPaquete nuevaDescripcionPaquete, @PathVariable Long id) {
        DescripcionPaquete descripcionPaqueteActualizado = repository.findById(id)
                .map(descripcionPaquete -> {
                    descripcionPaquete.setNombre(nuevaDescripcionPaquete.getNombre());
                    descripcionPaquete.setDescripcion(nuevaDescripcionPaquete.getDescripcion());
                    descripcionPaquete.setId_tipo_paquete(nuevaDescripcionPaquete.getId_tipo_paquete());
                    return repository.save(descripcionPaquete);
                })
                .orElseGet(() -> {
                    nuevaDescripcionPaquete.setId(id);
                    return repository.save(nuevaDescripcionPaquete);
                });
        EntityModel<DescripcionPaquete> entityModel = assembler.toModel(descripcionPaqueteActualizado);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteDescripcionPaquete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
