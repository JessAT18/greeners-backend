package com.nonbinsys.greeners.comercio;

import java.util.List;
import java.util.Optional;

public interface IComercioService {
    List<Comercio> listarComercios();
    Optional<Comercio> unComercio(Long id);
    Comercio guardarComercio(Comercio comercio);
    void eliminarComercio(Long id);

    List<Comercio> encontrarComerciosPorNombre(String nombreComercio);
}
