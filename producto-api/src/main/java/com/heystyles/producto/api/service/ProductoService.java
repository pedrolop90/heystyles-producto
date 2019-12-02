package com.heystyles.producto.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.producto.core.domain.Marca;
import com.heystyles.producto.core.domain.Producto;
import com.heystyles.producto.core.domain.ProductoExtended;
import com.heystyles.producto.core.dto.ProductoExtendedListResponse;
import com.heystyles.producto.core.dto.ProductoRequest;
import com.heystyles.producto.core.filter.ProductoFilter;

import java.util.List;

public interface ProductoService extends Service<Producto, Long> {

    Long insert(ProductoRequest request);

    void update(ProductoRequest request);

    void activarProducto(Long productoId);

    Producto getProducto(Long productoId);

    ProductoExtended getProductoExtended(Long productoId);

    ProductoExtendedListResponse filter(ProductoFilter filter);

    List<Marca> findMarcaByProductoId(Long productoId);
}
