package com.heystyles.producto.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.producto.api.dao.UnidadMedidaDao;
import com.heystyles.producto.api.entity.UnidadMedidaEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.core.domain.UnidadMedida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class UnidadMedidaToUnidadMedidaEntityConverter implements Converter<UnidadMedida, UnidadMedidaEntity> {

    @Autowired
    private UnidadMedidaDao unidadMedidaDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public UnidadMedidaEntity convert(UnidadMedida bean) {
        UnidadMedidaEntity entity = null;
        if (bean.getId() == null) {
            entity = new UnidadMedidaEntity();
        }
        else {
            entity = Optional.ofNullable(unidadMedidaDao.findOne(bean.getId()))
                    .orElseThrow(() ->
                            APIExceptions.objetoNoEncontrado(
                                    messageSource.getMessage(
                                            MessageKeys.UNIDAD_MEDIDA_NOT_FOUND,
                                            new String[]{String.valueOf(bean.getId())},
                                            getLocale()
                                    )
                            ));
        }
        entity.setNombre(bean.getNombre());
        entity.setAbreviatura(bean.getAbreviatura());
        return entity;
    }
}
