package com.nonbinsys.greeners.repositories;

import com.nonbinsys.greeners.entities.Comercio;
import com.nonbinsys.greeners.entities.producto.Producto;
import com.nonbinsys.greeners.entities.producto.ProductoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, ProductoId> {
}
