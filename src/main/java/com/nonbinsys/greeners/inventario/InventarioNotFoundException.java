package com.nonbinsys.greeners.inventario;

public class InventarioNotFoundException extends RuntimeException{
    public InventarioNotFoundException(Long id) {
        super("No se encontro el inventario " + id);
    }
}
