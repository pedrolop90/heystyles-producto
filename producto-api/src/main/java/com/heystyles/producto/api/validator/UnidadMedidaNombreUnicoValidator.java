package com.heystyles.producto.api.validator;

import com.heystyles.common.validation.ValidationError;
import com.heystyles.common.validation.Validator;
import com.heystyles.common.validation.groups.Insert;
import com.heystyles.common.validation.groups.Update;
import com.heystyles.producto.api.dao.UnidadMedidaDao;
import com.heystyles.producto.api.entity.UnidadMedidaEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.core.domain.UnidadMedida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class UnidadMedidaNombreUnicoValidator implements Validator<UnidadMedida> {

    @Autowired
    private UnidadMedidaDao unidadMedidaDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<ValidationError> validate(UnidadMedida unidadMedida) {
        List<ValidationError> errors = new ArrayList<>();
        UnidadMedidaEntity unidadMedidaEntity = unidadMedidaDao.findByNombre(unidadMedida.getNombre());
        if (unidadMedidaEntity != null && !Objects.equals(unidadMedida.getId(), unidadMedidaEntity.getId())) {
            errors.add(new ValidationError(
                    "nombre",
                    messageSource.getMessage(
                            MessageKeys.UNIDAD_MEDIDA_NOMBRE_DUPLICATED,
                            new String[]{unidadMedida.getNombre()},
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
