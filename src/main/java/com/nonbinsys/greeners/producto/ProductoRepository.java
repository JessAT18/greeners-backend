package com.nonbinsys.greeners.producto;

import com.nonbinsys.greeners.producto.entity.Producto;
import com.nonbinsys.greeners.producto.entity.ProductoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, ProductoId> {
}
