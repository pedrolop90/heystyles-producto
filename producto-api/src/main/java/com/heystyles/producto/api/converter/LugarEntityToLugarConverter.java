package com.heystyles.producto.api.converter;

import com.heystyles.producto.api.entity.LugarEntity;
import com.heystyles.producto.core.domain.Lugar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LugarEntityToLugarConverter implements Converter<LugarEntity, Lugar> {

    @Override
    public Lugar convert(LugarEntity entity) {
        Lugar bean = new Lugar();
        bean.setId(entity.getId());
        bean.setNombre(entity.getNombre());
        bean.setPosicion(entity.getPosicion());
        return bean;
    }
}
