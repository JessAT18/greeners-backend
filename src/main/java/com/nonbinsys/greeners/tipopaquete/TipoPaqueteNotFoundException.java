package com.nonbinsys.greeners.tipopaquete;

public class TipoPaqueteNotFoundException extends RuntimeException{
    public TipoPaqueteNotFoundException(Long id) {
        super("No se encontro el tipo de inventario " + id);
    }
}