package com.nonbinsys.greeners.inventario;

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
@RequestMapping("/api/inventarios")
public class InventarioController {
    private final InventarioRepository repository;
    private final InventarioModelAssembler assembler;

    public InventarioController(InventarioRepository repository, InventarioModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/listarInventarios")
    public CollectionModel<EntityModel<Inventario>> all() {
        List<EntityModel<Inventario>> inventario = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inventario, linkTo(methodOn(InventarioController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Inventario> one(@PathVariable Long id) {
        Inventario inventario = repository.findById(id)
                .orElseThrow(() -> new InventarioNotFoundException(id));

        return assembler.toModel(inventario);
    }

    @GetMapping("/encontrarInventarioPorComercio/{id_comercio}")
    public List<InventarioPorComercioDTO> encontrarInventarioPorComercio(@PathVariable Long id_comercio) {
        List<InventarioPorComercioDTO> inventariosPorComercio = repository.encontrarInventarioPorComercio(id_comercio).stream().toList();
        return inventariosPorComercio;
    }

    @PostMapping("/crearNuevoInventario")
    ResponseEntity<?> nuevoInventario(@RequestBody Inventario nuevoInventario) {
        EntityModel<Inventario> entityModel = assembler.toModel(repository.save(nuevoInventario));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
