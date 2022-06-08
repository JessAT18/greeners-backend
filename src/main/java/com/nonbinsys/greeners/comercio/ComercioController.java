package com.nonbinsys.greeners.comercio;

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
@RequestMapping("/api/comercios")
public class ComercioController {
    private final ComercioRepository repository;
    private final ComercioModelAssembler assembler;

    ComercioController(ComercioRepository repository, ComercioModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/listarComercios")
    public CollectionModel<EntityModel<Comercio>> all() {
        List<EntityModel<Comercio>> comercios = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(comercios, linkTo(methodOn(ComercioController.class).all()).withSelfRel());
    }

    @PostMapping("/crearNuevoComercio")
    ResponseEntity<?> nuevoComercio (@RequestBody Comercio nuevoComercio) {
        EntityModel<Comercio> entityModel = assembler.toModel(repository.save(nuevoComercio));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Comercio> one(@PathVariable Long id) {
        Comercio comercio = repository.findById(id)
                .orElseThrow(() -> new ComercioNotFoundException(id));

        return assembler.toModel(comercio);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceComercio(@RequestBody Comercio nuevoComercio, @PathVariable Long id) {
        Comercio comercioActualizado = repository.findById(id)
                .map(comercio -> {
                    comercio.setNombre(nuevoComercio.getNombre());
                    comercio.setTelf(nuevoComercio.getTelf());
                    comercio.setDireccion(nuevoComercio.getDireccion());
                    comercio.setId_adm_comercio(nuevoComercio.getId_adm_comercio());
                    return repository.save(comercio);
                })
                .orElseGet(() -> {
                    nuevoComercio.setId(id);
                    return repository.save(nuevoComercio);
                });
        EntityModel<Comercio> entityModel = assembler.toModel(comercioActualizado);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteComercio(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
