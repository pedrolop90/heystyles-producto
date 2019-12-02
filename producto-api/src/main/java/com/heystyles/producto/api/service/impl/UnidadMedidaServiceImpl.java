package com.heystyles.producto.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.common.types.Estado;
import com.heystyles.producto.api.dao.UnidadMedidaDao;
import com.heystyles.producto.api.entity.UnidadMedidaEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.api.service.UnidadMedidaService;
import com.heystyles.producto.core.domain.UnidadMedida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class UnidadMedidaServiceImpl
        extends ServiceImpl<UnidadMedida, UnidadMedidaEntity, Long> implements UnidadMedidaService {

    @Autowired
    private UnidadMedidaDao unidadMedidaDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<UnidadMedidaEntity, Long> getDao() {
        return unidadMedidaDao;
    }

    @Override
    public void delete(Long unidadMedidaId) {
        UnidadMedida unidadMedida = getUnidadMedida(unidadMedidaId);
        unidadMedida.setEstado(Estado.INACTIVO);
        update(unidadMedida);
    }

    @Override
    public UnidadMedida getUnidadMedida(Long unidadMedidaId) {
        return Optional.ofNullable(findById(unidadMedidaId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.UNIDAD_MEDIDA_NOT_FOUND,
                                new String[]{String.valueOf(unidadMedidaId)},
                                getLocale())
                ));
    }

    @Override
    public void activarUnidadMedida(Long unidadMedidaId) {
        UnidadMedida unidadMedida = getUnidadMedida(unidadMedidaId);
        unidadMedida.setEstado(Estado.ACTIVO);
        update(unidadMedida);
    }
}
