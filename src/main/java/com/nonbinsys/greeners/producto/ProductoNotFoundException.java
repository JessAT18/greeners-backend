package com.nonbinsys.greeners.producto;

import com.nonbinsys.greeners.producto.entity.ProductoId;

public class ProductoNotFoundException extends RuntimeException{
    public ProductoNotFoundException(ProductoId id) {
        super("No se encontro el producto. Codigo: " + id.getCodigo() + ", Id_comercio: " + id.getId_comercio());
    }
}
