package com.nonbinsys.greeners.paquete;

public class PaqueteNotFoundException extends RuntimeException{
    public PaqueteNotFoundException(Long id) {
        super("No se encontro la descripcion del inventario " + id);
    }
}
