package com.heystyles.producto.api.validator;

import com.heystyles.common.validation.ValidationError;
import com.heystyles.common.validation.Validator;
import com.heystyles.producto.api.dao.MarcaProductoDao;
import com.heystyles.producto.api.entity.MarcaProductoEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.core.domain.Marca;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class MarcaProductoReferenciadaValidator implements Validator<Marca> {

    @Autowired
    private MarcaProductoDao marcaProductoDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<ValidationError> validate(Marca marca) {
        List<ValidationError> errors = new ArrayList<>();
        List<MarcaProductoEntity> marcaProductoEntities = marcaProductoDao.findByMarcaId(marca.getId());
        if (marcaProductoEntities.size() > 0) {
            errors.add(new ValidationError(
                    "productos-activos",
                    messageSource.getMessage(
                            MessageKeys.MARCA_PRODUCTO_REFERENCIADOS,
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
