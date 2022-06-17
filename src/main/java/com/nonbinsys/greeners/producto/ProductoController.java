package com.nonbinsys.greeners.producto;

import com.nonbinsys.greeners.producto.entity.Producto;
import com.nonbinsys.greeners.producto.entity.ProductoId;
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
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private IProductoService iProductoService;
    private final ProductoModelAssembler assembler;

    ProductoController(ProductoModelAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/listarProductos")
    public CollectionModel<EntityModel<Producto>> listarProductos() {
        List<EntityModel<Producto>> productos = iProductoService.listarProductos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos, linkTo(methodOn(ProductoController.class).listarProductos()).withSelfRel());
    }

    @PostMapping("/crearNuevoProducto")
    ResponseEntity<?> nuevoProducto (@RequestBody Producto nuevoProducto) {
        EntityModel<Producto> entityModel = assembler.toModel(iProductoService.guardarProducto(nuevoProducto));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/{id_comercio}/{codigo}")
    public EntityModel<Producto> unProducto(@PathVariable Long id_comercio, @PathVariable String codigo) {
        ProductoId productoId = new ProductoId(id_comercio, codigo);
        Producto producto = iProductoService.unProducto(productoId)
                .orElseThrow(() -> new ProductoNotFoundException(productoId));

        return assembler.toModel(producto);
    }

    @PutMapping("/{id_comercio}/{codigo}")
    ResponseEntity<?> replaceEmployee(@RequestBody Producto nuevoProducto, @PathVariable Long id_comercio, @PathVariable String codigo) {
        ProductoId productoId = new ProductoId(id_comercio, codigo);
        Producto productoActualizado = iProductoService.unProducto(productoId)
                .map(producto -> {
                    producto.setNombre(nuevoProducto.getNombre());
                    producto.setDescripcion(nuevoProducto.getDescripcion());
                    return iProductoService.guardarProducto(producto);
                })
                .orElseGet(() -> {
                    nuevoProducto.setId_comercio(id_comercio);
                    nuevoProducto.setCodigo(codigo);
                    return iProductoService.guardarProducto(nuevoProducto);
                });
        EntityModel<Producto> entityModel = assembler.toModel(productoActualizado);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id_comercio}/{codigo}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id_comercio, @PathVariable String codigo) {
        ProductoId productoId = new ProductoId(id_comercio, codigo);
        iProductoService.eliminarProducto(productoId);
        return ResponseEntity.noContent().build();
    }
}
