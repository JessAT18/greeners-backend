package com.nonbinsys.greeners.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    String paquetePorComercioQuery = "SELECT paquetes.id AS id, inventarios.codigo AS codigo, " +
            "inventarios.precio AS precio, paquetes.nombre AS nombre, paquetes.descripcion AS descripcion, " +
            "paquetes.link_paquete AS link_paquete FROM inventarios\n" +
            "\tLEFT JOIN paquetes ON inventarios.id_paquete = paquetes.id\n" +
            "    WHERE inventarios.id_comercio= :id_comercio\n" +
            "    ORDER BY fecha DESC";

    String paquetePorComercioQuery2 = "SELECT new com.nonbinsys.greeners.inventario.InventarioPorComercioDTO(p.id, i.codigo, i.precio, p.nombre, p.descripcion, p.link_paquete) FROM Inventario i\n" +
            "\tLEFT JOIN Paquete p ON i.id_paquete = p.id\n" +
            "    WHERE i.id_comercio=:id_comercio\n" +
            "    ORDER BY fecha DESC";

    @Query(value = paquetePorComercioQuery2)
    List<InventarioPorComercioDTO> encontrarInventarioPorComercio(@Param("id_comercio") Long id_comercio);
}
