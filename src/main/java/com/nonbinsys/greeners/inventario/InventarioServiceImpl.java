package com.nonbinsys.greeners.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioServiceImpl implements IInventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public List<Inventario> listarInventarios() {
        return inventarioRepository.findAll();
    }

    @Override
    public Optional<Inventario> unInventario(Long id) {
        return inventarioRepository.findById(id);
    }

    @Override
    public Inventario guardarInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public List<InventarioPorComercioDTO> encontrarInventariosPorComercio(Long id_comercio) {
        return inventarioRepository.encontrarInventarioPorComercio(id_comercio);
    }
}
