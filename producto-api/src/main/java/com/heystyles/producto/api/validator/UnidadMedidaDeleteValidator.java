package com.heystyles.producto.api.validator;

import com.heystyles.common.validation.ValidationError;
import com.heystyles.common.validation.Validator;
import com.heystyles.common.validation.groups.Delete;
import com.heystyles.producto.api.dao.ProductoDao;
import com.heystyles.producto.api.entity.ProductoEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.core.domain.UnidadMedida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class UnidadMedidaDeleteValidator implements Validator<UnidadMedida> {

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<ValidationError> validate(UnidadMedida unidadMedida) {
        List<ValidationError> errors = new ArrayList<>();
        List<ProductoEntity> productoEntities = productoDao.findByUnidadMedidaId(unidadMedida.getId());
        if (!productoEntities.isEmpty()) {
            errors.add(new ValidationError(
                    "productos-referenciados",
                    messageSource.getMessage(MessageKeys.UNIDAD_MEDIDA_DELETE_PRODUCTOS_REFERENCIADOS,
                            null, getLocale())
            ));
        }
        return errors;
    }

    @Override
    public List<Class<?>> getScopes() {
        return Arrays.asList(Delete.class);
    }
}
