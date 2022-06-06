package com.nonbinsys.greeners.exceptions;

public class TipoPaqueteNotFoundException extends RuntimeException{
    public TipoPaqueteNotFoundException(Long id) {
        super("No se encontro el tipo de paquete " + id);
    }
}
