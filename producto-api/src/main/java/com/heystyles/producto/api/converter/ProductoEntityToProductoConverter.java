package com.heystyles.producto.api.converter;

import com.heystyles.producto.api.entity.ProductoEntity;
import com.heystyles.producto.core.domain.Producto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductoEntityToProductoConverter implements Converter<ProductoEntity, Producto> {

    @Override
    public Producto convert(ProductoEntity entity) {
        Producto bean = new Producto();
        bean.setId(entity.getId());
        bean.setNombre(entity.getNombre());
        bean.setStockMinimo(entity.getStockMinimo());
        bean.setUnidadMedidaId(entity.getUnidadMedida().getId());
        return bean;
    }
}
