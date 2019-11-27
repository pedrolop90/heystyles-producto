package com.heystyles.producto.api.converter;

import com.heystyles.producto.api.entity.UnidadMedidaEntity;
import com.heystyles.producto.core.domain.UnidadMedida;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnidadMedidaEntityToUnidadMedidaConverter implements Converter<UnidadMedidaEntity, UnidadMedida> {

    @Override
    public UnidadMedida convert(UnidadMedidaEntity entity) {
        UnidadMedida bean = new UnidadMedida();
        bean.setId(entity.getId());
        bean.setNombre(entity.getNombre());
        bean.setAbreviatura(entity.getAbreviatura());
        return bean;
    }
}
