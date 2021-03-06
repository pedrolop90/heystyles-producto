package com.heystyles.producto.api.converter;

import com.heystyles.common.service.ConverterService;
import com.heystyles.producto.api.entity.MarcaEntity;
import com.heystyles.producto.api.entity.ProductoEntity;
import com.heystyles.producto.api.service.MarcaProductoService;
import com.heystyles.producto.core.domain.Marca;
import com.heystyles.producto.core.domain.ProductoExtended;
import com.heystyles.producto.core.domain.UnidadMedida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductoEntityToProductoExtendedConverter implements Converter<ProductoEntity, ProductoExtended> {

    @Autowired
    private ConverterService converterService;

    @Autowired
    private MarcaProductoService marcaProductoService;

    @Override
    public ProductoExtended convert(ProductoEntity entity) {
        ProductoExtended bean = new ProductoExtended();
        bean.setId(entity.getId());
        bean.setNombre(entity.getNombre());
        bean.setStockMinimo(entity.getStockMinimo());
        bean.setEstado(entity.getEstado());
        bean.setUnidadMedida(converterService.convertTo(entity.getUnidadMedida(), UnidadMedida.class));
        List<MarcaEntity> marcaEntities = marcaProductoService.findMarcaByProductoId(entity.getId());
        bean.setMarcas(converterService.convertTo(marcaEntities, Marca.class));
        return bean;
    }
}
