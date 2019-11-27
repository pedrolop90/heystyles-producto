package com.heystyles.producto.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.producto.api.dao.LugarDao;
import com.heystyles.producto.api.entity.LugarEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.api.service.LugarService;
import com.heystyles.producto.core.domain.Lugar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class LugarServiceImpl
        extends ServiceImpl<Lugar, LugarEntity, Long> implements LugarService {

    @Autowired
    private LugarDao lugarDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<LugarEntity, Long> getDao() {
        return lugarDao;
    }

    @Override
    public Lugar getLugar(Long lugarId) {
        return Optional.ofNullable(findById(lugarId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(
                                MessageKeys.LUGAR_NOT_FOUND,
                                new String[]{String.valueOf(lugarId)},
                                getLocale()
                        )
                ));
    }
}
