package com.nonbinsys.greeners.producto;

import com.nonbinsys.greeners.producto.entity.Producto;
import com.nonbinsys.greeners.producto.entity.ProductoId;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> listarProductos();
    Optional<Producto> unProducto(ProductoId id);
    Producto guardarProducto(Producto producto);
    void eliminarProducto(ProductoId id);
}
