package com.heystyles.producto.api.controller;

import com.heystyles.common.service.ConverterService;
import com.heystyles.producto.api.entity.MarcaProductoEntity;
import com.heystyles.producto.core.domain.Marca;
import com.heystyles.producto.core.domain.ProductoExtended;
import com.heystyles.producto.core.dto.MarcaProductoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MarcaProductoEntityToMarcaProductoCoverter implements Converter<MarcaProductoEntity, MarcaProductoDto> {

    @Autowired
    private ConverterService converterService;

    @Override
    public MarcaProductoDto convert(MarcaProductoEntity entity) {
        MarcaProductoDto dto = new MarcaProductoDto();
        dto.setId(entity.getId());
        dto.setMarca(converterService.convertTo(entity.getMarca(), Marca.class));
        dto.setProducto(converterService.convertTo(entity.getProducto(), ProductoExtended.class));
        dto.setStockMinimo(entity.getStockMinimo());
        return dto;
    }
}
