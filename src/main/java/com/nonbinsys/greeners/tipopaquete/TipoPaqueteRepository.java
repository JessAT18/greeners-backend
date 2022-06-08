package com.nonbinsys.greeners.tipopaquete;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPaqueteRepository extends JpaRepository<TipoPaquete, Long> {
    class TipoPaqueteNotFoundException extends RuntimeException{
        public TipoPaqueteNotFoundException(Long id) {
            super("No se encontro el tipo de paquete " + id);
        }
    }
}
