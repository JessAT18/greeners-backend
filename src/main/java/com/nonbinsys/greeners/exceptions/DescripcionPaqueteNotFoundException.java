package com.nonbinsys.greeners.exceptions;

public class DescripcionPaqueteNotFoundException extends RuntimeException{
    public DescripcionPaqueteNotFoundException(Long id) {
        super("No se encontro la descripcion del paquete " + id);
    }
}
