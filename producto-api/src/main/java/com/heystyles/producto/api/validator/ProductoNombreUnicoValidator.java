package com.heystyles.producto.api.validator;

import com.heystyles.common.validation.ValidationError;
import com.heystyles.common.validation.Validator;
import com.heystyles.common.validation.groups.Insert;
import com.heystyles.common.validation.groups.Update;
import com.heystyles.producto.api.dao.ProductoDao;
import com.heystyles.producto.api.entity.ProductoEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.core.domain.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class ProductoNombreUnicoValidator implements Validator<Producto> {

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<ValidationError> validate(Producto producto) {
        List<ValidationError> errors = new ArrayList<>();
        ProductoEntity productoEntity = productoDao.findByNombre(producto.getNombre());
        if (productoEntity != null && !Objects.equals(producto.getId(), productoEntity.getId())) {
            errors.add(new ValidationError(
                    "nombre",
                    messageSource.getMessage(
                            MessageKeys.PRODUCTO_NOMBRE_DUPLICATED,
                            new String[]{producto.getNombre()},
                            getLocale())
            ));
        }
        return errors;
    }

    @Override
    public List<Class<?>> getScopes() {
        return Arrays.asList(Insert.class, Update.class);
    }
}
