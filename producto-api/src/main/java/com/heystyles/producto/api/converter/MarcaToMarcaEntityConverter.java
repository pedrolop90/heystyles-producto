package com.heystyles.producto.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.producto.api.dao.MarcaDao;
import com.heystyles.producto.api.entity.MarcaEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.core.domain.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class MarcaToMarcaEntityConverter implements Converter<Marca, MarcaEntity> {

    @Autowired
    private MarcaDao marcaDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public MarcaEntity convert(Marca bean) {
        MarcaEntity entity = null;
        if (bean.getId() == null) {
            entity = new MarcaEntity();
        }
        else {
            entity = Optional.ofNullable(marcaDao.findOne(bean.getId()))
                    .orElseThrow(() ->
                            APIExceptions.objetoNoEncontrado(
                                    messageSource.getMessage(
                                            MessageKeys.MARCA_NOT_FOUND,
                                            new String[]{String.valueOf(bean.getId())},
                                            getLocale()
                                    )
                            ));
        }
        entity.setNombre(bean.getNombre());
        entity.setDescripcion(bean.getDescripcion());
        return entity;
    }
}
