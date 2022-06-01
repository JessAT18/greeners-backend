package com.nonbinsys.greeners.controllers;

import com.nonbinsys.greeners.assembler.ComercioModelAssembler;
import com.nonbinsys.greeners.entities.Comercio;
import com.nonbinsys.greeners.exceptions.ComercioNotFoundException;
import com.nonbinsys.greeners.repositories.ComercioRepository;
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
    ResponseEntity<?> replaceEmployee(@RequestBody Comercio nuevoComercio, @PathVariable Long id) {
        Comercio updatedEmployee = repository.findById(id)
                .map(employee -> {
                    employee.setNombre(nuevoComercio.getNombre());
                    employee.setTelf(nuevoComercio.getTelf());
                    employee.setDireccion(nuevoComercio.getDireccion());
                    employee.setId_adm_comercio(nuevoComercio.getId_adm_comercio());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    nuevoComercio.setId(id);
                    return repository.save(nuevoComercio);
                });
        EntityModel<Comercio> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
