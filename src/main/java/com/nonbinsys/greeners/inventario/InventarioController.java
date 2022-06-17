package com.nonbinsys.greeners.inventario;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private IInventarioService iInventarioService;
    private final InventarioModelAssembler assembler;

    public InventarioController(InventarioModelAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/listarInventarios")
    public CollectionModel<EntityModel<Inventario>> listarInventarios() {
        List<EntityModel<Inventario>> inventario = iInventarioService.listarInventarios().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inventario, linkTo(methodOn(InventarioController.class).listarInventarios()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Inventario> unInventario(@PathVariable Long id) {
        Inventario inventario = iInventarioService.unInventario(id)
                .orElseThrow(() -> new InventarioNotFoundException(id));

        return assembler.toModel(inventario);
    }

    @GetMapping("/encontrarInventarioPorComercio/{id_comercio}")
    public List<InventarioPorComercioDTO> encontrarInventarioPorComercio(@PathVariable Long id_comercio) {
        List<InventarioPorComercioDTO> inventariosPorComercio = iInventarioService.encontrarInventariosPorComercio(id_comercio).stream().toList();
        return inventariosPorComercio;
    }

    @PostMapping("/crearNuevoInventario")
    ResponseEntity<?> nuevoInventario(@RequestBody Inventario nuevoInventario) {
        EntityModel<Inventario> entityModel = assembler.toModel(iInventarioService.guardarInventario(nuevoInventario));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
