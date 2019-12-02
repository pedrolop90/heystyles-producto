package com.heystyles.producto.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.common.types.Estado;
import com.heystyles.common.types.Page;
import com.heystyles.producto.api.dao.LugarDao;
import com.heystyles.producto.api.entity.LugarEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.api.service.LugarService;
import com.heystyles.producto.core.domain.Lugar;
import com.heystyles.producto.core.dto.LugarListResponse;
import com.heystyles.producto.core.filter.LugarFilter;
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
    public void delete(Long lugarId) {
        Lugar lugar = getLugar(lugarId);
        lugar.setEstado(Estado.INACTIVO);
        super.update(lugar);
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

    @Override
    public void activarLugar(Long lugarId) {
        Lugar lugar = getLugar(lugarId);
        lugar.setEstado(Estado.ACTIVO);
        super.update(lugar);
    }

    @Override
    public LugarListResponse filter(LugarFilter filter) {
        filter = Optional.ofNullable(filter).orElse(new LugarFilter());
        Page<LugarEntity> page = lugarDao.getPage(filter);
        return new LugarListResponse(
                page.getTotalElements(),
                getConverterService().convertTo(page.getContent(), Lugar.class)
        );
    }
}
