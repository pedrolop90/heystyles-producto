package com.heystyles.producto.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.producto.core.domain.Marca;
import com.heystyles.producto.core.domain.Producto;
import com.heystyles.producto.core.domain.ProductoExtended;
import com.heystyles.producto.core.dto.ProductoRequest;

import java.util.List;

public interface ProductoService extends Service<Producto, Long> {

    Long insert(ProductoRequest request);

    void update(ProductoRequest request);

    ProductoExtended getProducto(Long productoId);

    List<ProductoExtended> findAllProductoExtended();

    List<Marca> findMarcaByProductoId(Long productoId);
}
