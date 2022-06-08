package com.nonbinsys.greeners.comercio;

public class ComercioNotFoundException extends RuntimeException {
    public ComercioNotFoundException(Long id) {
        super("No se encontro el comercio " + id);
    }
}
