package com.nonbinsys.greeners.tipopaquete;

import java.util.List;
import java.util.Optional;

public interface ITipoPaqueteService {
    List<TipoPaquete> listarTiposPaquete();
    Optional<TipoPaquete> unTipoPaquete(Long id);
}
