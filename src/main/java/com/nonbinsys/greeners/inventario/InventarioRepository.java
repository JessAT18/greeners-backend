package com.nonbinsys.greeners.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    String paquetePorComercioQuery = "SELECT paquetes.id, inventarios.codigo, inventarios.precio, paquetes.nombre, paquetes.descripcion, paquetes.link_paquete FROM inventarios\n" +
            "\tLEFT JOIN paquetes ON inventarios.id_paquete = paquetes.id\n" +
            "    WHERE inventarios.id_comercio= :id_comercio\n" +
            "    ORDER BY fecha DESC";

    @Query(value = paquetePorComercioQuery, nativeQuery = true)
    List<InventarioPorComercioDTO> encontrarInventarioPorComercio(@Param("id_comercio") Long id_comercio);
}
