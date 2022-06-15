package com.nonbinsys.greeners.paquete;

import com.nonbinsys.greeners.inventario.Inventario;
import com.nonbinsys.greeners.inventario.InventarioController;
import com.nonbinsys.greeners.inventario.InventarioModelAssembler;
import com.nonbinsys.greeners.inventario.InventarioRepository;
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
    private final InventarioRepository inventarioRepository;
    private final InventarioModelAssembler inventarioAssembler;

    public PaqueteController(PaqueteRepository repository, PaqueteModelAssembler assembler, InventarioRepository inventarioRepository, InventarioModelAssembler inventarioAssembler) {
        this.repository = repository;
        this.assembler = assembler;
        this.inventarioRepository = inventarioRepository;
        this.inventarioAssembler = inventarioAssembler;
    }

    @GetMapping("/listarPaquetes")
    public CollectionModel<EntityModel<Paquete>> all() {
        List<EntityModel<Paquete>> descripcionesPaquetes = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(descripcionesPaquetes, linkTo(methodOn(PaqueteController.class).all()).withSelfRel());
    }


    @GetMapping("/encontrarPaquetesPorComercio/{id_comercio}")
    public CollectionModel<EntityModel<Paquete>> encontrarPaquetesPorComercio(@PathVariable Long id_comercio) {
        List<EntityModel<Paquete>> descripcionesPaquetes = repository.encontrarPaquetesPorComercio(id_comercio).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(descripcionesPaquetes, linkTo(methodOn(PaqueteController.class).encontrarPaquetesPorComercio(id_comercio)).withSelfRel());
    }

    @PostMapping("/crearNuevoPaquete")
    ResponseEntity<?> nuevoPaquete (@RequestBody PaqueteInventario nuevo) {

        //Pasando atributos de Paquete a PaqueteInventario
        Paquete nuevoPaquete = new Paquete();
        nuevoPaquete.setNombre(nuevo.getNombre());
        nuevoPaquete.setDescripcion(nuevo.getDescripcion());
        //nuevoPaquete.setLink_paquete(nuevo.getLink_paquete());
        nuevoPaquete.setLink_paquete("https://i.imgur.com/i2Pyx6A.png");
        nuevoPaquete.setHabilitado(nuevo.getHabilitado());
        nuevoPaquete.setId_tipo_paquete(nuevo.getId_tipo_paquete());
        nuevoPaquete.setId_comercio(nuevo.getId_comercio());

        EntityModel<Paquete> entityModel = assembler.toModel(repository.save(nuevoPaquete));
        if (nuevoPaquete.getHabilitado() == false) {
            return ResponseEntity //
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                    .body(entityModel);
        }
        else{
            //SI ESTA HABILITADO
            //OBTENIENDO ID PAQUETE PARA INSERTAR EN INVENTARIO
            Long id_paquete = entityModel.getContent().getId();

            Inventario nuevoInventario = new Inventario();
            nuevoInventario.setCodigo(nuevo.getCodigo());
            nuevoInventario.setPrecio(nuevo.getPrecio());
            nuevoInventario.setStock(nuevo.getStock());
            nuevoInventario.setId_comercio(nuevo.getId_comercio());
            nuevoInventario.setId_paquete(id_paquete);

            EntityModel<Inventario> inventarioEntityModel = inventarioAssembler.toModel(inventarioRepository.save(nuevoInventario));

            return ResponseEntity
                    .created(inventarioEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(inventarioEntityModel);
        }
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
                    if (nuevoPaquete.getLink_paquete() == null || nuevoPaquete.getLink_paquete().isEmpty())
                    {
                        paquete.setLink_paquete("https://i.imgur.com/i2Pyx6A.png");
                    }
                    else {
                        paquete.setLink_paquete(nuevoPaquete.getLink_paquete());
                    }
                    if (nuevoPaquete.getHabilitado() == null)
                    {
                        paquete.setHabilitado(false);
                    }
                    else {
                        paquete.setHabilitado(nuevoPaquete.getHabilitado());
                    }
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
