package com.heystyles.producto.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.producto.api.dao.ProductoDao;
import com.heystyles.producto.api.dao.UnidadMedidaDao;
import com.heystyles.producto.api.entity.ProductoEntity;
import com.heystyles.producto.api.entity.UnidadMedidaEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.core.domain.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class ProductoToProductoEntityConverter implements Converter<Producto, ProductoEntity> {

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private UnidadMedidaDao unidadMedidaDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ProductoEntity convert(Producto bean) {
        ProductoEntity entity = null;
        if (bean.getId() == null) {
            entity = new ProductoEntity();
        }
        else {
            entity = Optional.ofNullable(productoDao.findOne(bean.getId()))
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
        entity.setStockMinimo(bean.getStockMinimo());
        UnidadMedidaEntity unidadMedidaEntity = Optional.ofNullable(unidadMedidaDao.findOne(bean.getUnidadMedidaId()))
                .orElseThrow(() ->
                        APIExceptions.objetoNoEncontrado(
                                messageSource.getMessage(
                                        MessageKeys.UNIDAD_MEDIDA_NOT_FOUND,
                                        new String[]{String.valueOf(bean.getUnidadMedidaId())},
                                        getLocale()
                                )
                        ));
        entity.setUnidadMedida(unidadMedidaEntity);
        entity.setEstado(bean.getEstado());
        return entity;
    }
}
