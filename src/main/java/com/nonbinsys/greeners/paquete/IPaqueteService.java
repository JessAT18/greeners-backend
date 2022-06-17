package com.nonbinsys.greeners.paquete;

import java.util.List;
import java.util.Optional;

public interface IPaqueteService {
    List<Paquete> listarPaquetes();
    Optional<Paquete> unPaquete(Long id);
    Paquete guardarPaquete(Paquete paquete);
    void eliminarPaquete(Long id);

    List<Paquete> encontrarPaquetesPorComercio(Long id_comercio);
}
