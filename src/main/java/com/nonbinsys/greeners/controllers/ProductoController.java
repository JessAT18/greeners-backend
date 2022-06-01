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

    @GetMapping("/{id_comercio}/{codigo}")
    public EntityModel<Producto> one(@PathVariable Long id_comercio, @PathVariable String codigo) {
        ProductoId productoId = new ProductoId(id_comercio, codigo);
        Producto producto = repository.findById(productoId)
                .orElseThrow(() -> new ProductoNotFoundException(productoId));

        return assembler.toModel(producto);
    }

    @PutMapping("/{id_comercio}/{codigo}")
    ResponseEntity<?> replaceEmployee(@RequestBody Producto nuevoProducto, @PathVariable Long id_comercio, @PathVariable String codigo) {
        ProductoId productoId = new ProductoId(id_comercio, codigo);
        Producto productoActualizado = repository.findById(productoId)
                .map(producto -> {
                    producto.setNombre(nuevoProducto.getNombre());
                    producto.setDescripcion(nuevoProducto.getDescripcion());
                    return repository.save(producto);
                })
                .orElseGet(() -> {
                    nuevoProducto.setId_comercio(id_comercio);
                    nuevoProducto.setCodigo(codigo);
                    return repository.save(nuevoProducto);
                });
        EntityModel<Producto> entityModel = assembler.toModel(productoActualizado);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id_comercio}/{codigo}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id_comercio, @PathVariable String codigo) {
        ProductoId productoId = new ProductoId(id_comercio, codigo);
        repository.deleteById(productoId);
        return ResponseEntity.noContent().build();
    }
}
