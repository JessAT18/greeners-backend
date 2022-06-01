package com.nonbinsys.greeners.exceptions;

import com.nonbinsys.greeners.entities.producto.ProductoId;

public class ProductoNotFoundException extends RuntimeException{
    public ProductoNotFoundException(ProductoId id) {
        super("No se encontro el comercio " + id);
    }
}
