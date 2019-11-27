package com.heystyles.producto.api.validator;

import com.heystyles.common.validation.ValidationError;
import com.heystyles.common.validation.Validator;
import com.heystyles.common.validation.groups.Insert;
import com.heystyles.common.validation.groups.Update;
import com.heystyles.producto.api.dao.MarcaDao;
import com.heystyles.producto.api.entity.MarcaEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.core.domain.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class MarcaNombreUnicoValidator implements Validator<Marca> {

    @Autowired
    private MarcaDao marcaDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<ValidationError> validate(Marca marca) {
        List<ValidationError> errors = new ArrayList<>();
        MarcaEntity marcaEntity = marcaDao.findByNombre(marca.getNombre());
        if (marcaEntity != null && !Objects.equals(marca.getId(), marcaEntity.getId())) {
            errors.add(new ValidationError(
                    "nombre",
                    messageSource.getMessage(
                            MessageKeys.MARCA_NOMBRE_DUPLICATED,
                            new String[]{marca.getNombre()},
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
