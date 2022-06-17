package com.nonbinsys.greeners.producto;

import com.nonbinsys.greeners.producto.entity.Producto;
import com.nonbinsys.greeners.producto.entity.ProductoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements  IProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> unProducto(ProductoId id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(ProductoId id) {
        productoRepository.deleteById(id);
    }
}
