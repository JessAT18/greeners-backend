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
@RequestMapping("/api/paquetes")
public class PaqueteController {
    private final PaqueteRepository repository;
    private final PaqueteModelAssembler assembler;

    public PaqueteController(PaqueteRepository repository, PaqueteModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/listarPaquetes")
    public CollectionModel<EntityModel<Paquete>> all() {
        List<EntityModel<Paquete>> descripcionesPaquetes = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(descripcionesPaquetes, linkTo(methodOn(PaqueteController.class).all()).withSelfRel());
    }

    @PostMapping("/crearNuevoPaquete")
    ResponseEntity<?> nuevoPaquete (@RequestBody Paquete nuevoPaquete) {
        EntityModel<Paquete> entityModel = assembler.toModel(repository.save(nuevoPaquete));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Paquete> one(@PathVariable Long id) {
        Paquete paquete = repository.findById(id)
                .orElseThrow(() -> new PaqueteNotFoundException(id));

        return assembler.toModel(paquete);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replacePaquete(@RequestBody Paquete nuevoPaquete, @PathVariable Long id) {
        Paquete paqueteActualizado = repository.findById(id)
                .map(paquete -> {
                    paquete.setNombre(nuevoPaquete.getNombre());
                    paquete.setDescripcion(nuevoPaquete.getDescripcion());
                    paquete.setId_tipo_paquete(nuevoPaquete.getId_tipo_paquete());
                    return repository.save(paquete);
                })
                .orElseGet(() -> {
                    nuevoPaquete.setId(id);
                    return repository.save(nuevoPaquete);
                });
        EntityModel<Paquete> entityModel = assembler.toModel(paqueteActualizado);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePaquete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
