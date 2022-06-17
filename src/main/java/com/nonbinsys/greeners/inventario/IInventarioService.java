package com.nonbinsys.greeners.inventario;

import java.util.List;
import java.util.Optional;

public interface IInventarioService {
    List<Inventario> listarInventarios();
    Optional<Inventario> unInventario(Long id);
    Inventario guardarInventario(Inventario inventario);
    List<InventarioPorComercioDTO> encontrarInventariosPorComercio(Long id_comercio);
}
