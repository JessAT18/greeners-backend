package com.nonbinsys.greeners.comercio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    @Autowired
    private IComercioService iComercioService;
    private final ComercioModelAssembler assembler;

    ComercioController(ComercioModelAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/listarComercios")
    public CollectionModel<EntityModel<Comercio>> all() {
        List<EntityModel<Comercio>> comercios = iComercioService.listarComercios().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(comercios, linkTo(methodOn(ComercioController.class).all()).withSelfRel());
    }

    @PostMapping("/crearNuevoComercio")
    ResponseEntity<?> nuevoComercio (@RequestBody Comercio nuevoComercio) {
        EntityModel<Comercio> entityModel = assembler.toModel(iComercioService.guardarComercio(nuevoComercio));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Comercio> one(@PathVariable Long id) {
        Comercio comercio = iComercioService.unComercio(id)
                .orElseThrow(() -> new ComercioNotFoundException(id));
        return assembler.toModel(comercio);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceComercio(@RequestBody Comercio nuevoComercio, @PathVariable Long id) {
        Comercio comercioActualizado = iComercioService.unComercio(id)
                .map(comercio -> {
                    comercio.setNombre(nuevoComercio.getNombre());
                    comercio.setTelf(nuevoComercio.getTelf());
                    comercio.setDireccion(nuevoComercio.getDireccion());
                    comercio.setId_adm_comercio(nuevoComercio.getId_adm_comercio());
                    return iComercioService.guardarComercio(comercio);
                })
                .orElseGet(() -> {
                    nuevoComercio.setId(id);
                    return iComercioService.guardarComercio(nuevoComercio);
                });
        EntityModel<Comercio> entityModel = assembler.toModel(comercioActualizado);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteComercio(@PathVariable Long id) {
        iComercioService.eliminarComercio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/encontrarComerciosPorNombre/{nombre}")
    public CollectionModel<EntityModel<Comercio>> encontrarComerciosPorNombre(@PathVariable("nombre") String nombreComercio) {
        List<EntityModel<Comercio>> comercios = iComercioService.encontrarComerciosPorNombre(nombreComercio).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        // TODO: 6/8/2022 IMPLEMENTAR EXCEPCIONES

        return CollectionModel.of(comercios, linkTo(methodOn(ComercioController.class).all()).withSelfRel());
    }
}
