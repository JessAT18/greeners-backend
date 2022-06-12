package com.nonbinsys.greeners.paquete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaqueteRepository extends JpaRepository<Paquete, Long> {
    @Query(value = "SELECT p FROM Paquete p WHERE p.id_comercio=:id_comercio")
    List<Paquete> encontrarPaquetesPorComercio(@Param("id_comercio") Long id_comercio);
}
