package com.nonbinsys.greeners.controllers;

import com.nonbinsys.greeners.assembler.ProductoModelAssembler;
import com.nonbinsys.greeners.entities.producto.Producto;
import com.nonbinsys.greeners.entities.producto.ProductoId;
import com.nonbinsys.greeners.exceptions.ProductoNotFoundException;
import com.nonbinsys.greeners.repositories.ProductoRepository;
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
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoRepository repository;
    private final ProductoModelAssembler assembler;

    ProductoController(ProductoRepository repository, ProductoModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/listarProductos")
    public CollectionModel<EntityModel<Producto>> all() {
        List<EntityModel<Producto>> productos = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos, linkTo(methodOn(ProductoController.class).all()).withSelfRel());
    }

    @PostMapping("/crearNuevoProducto")
    ResponseEntity<?> nuevoComercio (@RequestBody Producto nuevoProducto) {
        EntityModel<Producto> entityModel = assembler.toModel(repository.save(nuevoProducto));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Producto> one(@PathVariable ProductoId id) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        return assembler.toModel(producto);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Producto nuevoProducto, @PathVariable ProductoId id) {
        Producto productoActualizado = repository.findById(id)
                .map(employee -> {
                    employee.setNombre(nuevoProducto.getNombre());
//                    employee.setTelf(nuevoProducto.getTelf());
//                    employee.setDireccion(nuevoProducto.getDireccion());
//                    employee.setId_adm_comercio(nuevoProducto.getId_adm_comercio());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    nuevoProducto.setProductoId(id);
                    return repository.save(nuevoProducto);
                });
        EntityModel<Producto> entityModel = assembler.toModel(productoActualizado);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable ProductoId id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
