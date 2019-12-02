package com.heystyles.producto.api.converter;

import com.heystyles.producto.api.entity.MarcaEntity;
import com.heystyles.producto.core.domain.Marca;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MarcaEntityToMarcaConverter implements Converter<MarcaEntity, Marca> {

    @Override
    public Marca convert(MarcaEntity entity) {
        Marca bean = new Marca();
        bean.setId(entity.getId());
        bean.setNombre(entity.getNombre());
        bean.setDescripcion(entity.getDescripcion());
        bean.setEstado(entity.getEstado());
        return bean;
    }
}
