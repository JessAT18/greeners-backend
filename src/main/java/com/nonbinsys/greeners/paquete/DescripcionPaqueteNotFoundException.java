package com.nonbinsys.greeners.paquete;

public class DescripcionPaqueteNotFoundException extends RuntimeException{
    public DescripcionPaqueteNotFoundException(Long id) {
        super("No se encontro la descripcion del paquete " + id);
    }
}
