package com.nonbinsys.greeners.exceptions;

public class ComercioNotFoundException extends RuntimeException {
    public ComercioNotFoundException(Long id) {
        super("No se encontro el comercio " + id);
    }
}
