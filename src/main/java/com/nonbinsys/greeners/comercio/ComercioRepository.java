package com.nonbinsys.greeners.comercio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComercioRepository extends JpaRepository<Comercio, Long> {
    @Query(value = "SELECT c FROM Comercio c WHERE c.nombre LIKE %:nombre%")
    List<Comercio> encontrarComerciosPorNombre(@Param("nombre") String nombre);
}
