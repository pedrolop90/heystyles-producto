package com.heystyles.producto.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.producto.api.dao.LugarDao;
import com.heystyles.producto.api.entity.LugarEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.core.domain.Lugar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class LugarToLugarEntityConverter implements Converter<Lugar, LugarEntity> {

    @Autowired
    private LugarDao lugarDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public LugarEntity convert(Lugar bean) {
        LugarEntity entity = null;
        if (bean.getId() == null) {
            entity = new LugarEntity();
        }
        else {
            entity = Optional.ofNullable(lugarDao.findOne(bean.getId()))
                    .orElseThrow(() ->
                            APIExceptions.objetoNoEncontrado(
                                    messageSource.getMessage(
                                            MessageKeys.LUGAR_NOT_FOUND,
                                            new String[]{String.valueOf(bean.getId())},
                                            getLocale()
                                    )
                            ));
        }
        entity.setNombre(bean.getNombre());
        entity.setPosicion(bean.getPosicion());
        entity.setEstado(bean.getEstado());
        return entity;
    }
}
