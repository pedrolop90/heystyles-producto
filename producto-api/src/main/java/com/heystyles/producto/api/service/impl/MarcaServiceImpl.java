package com.heystyles.producto.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.common.types.Estado;
import com.heystyles.producto.api.dao.MarcaDao;
import com.heystyles.producto.api.entity.MarcaEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.api.service.MarcaService;
import com.heystyles.producto.core.domain.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class MarcaServiceImpl
        extends ServiceImpl<Marca, MarcaEntity, Long> implements MarcaService {

    @Autowired
    private MarcaDao marcaDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<MarcaEntity, Long> getDao() {
        return marcaDao;
    }

    @Override
    public void delete(Long marcaId) {
        Marca marca = getMarca(marcaId);
        marca.setEstado(Estado.INACTIVO);
        update(marca);
    }

    @Override
    public Marca getMarca(Long marcaId) {
        return Optional.ofNullable(findById(marcaId)).orElseThrow(() ->
                APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.MARCA_NOT_FOUND,
                                new String[]{String.valueOf(marcaId)},
                                getLocale())
                ));
    }

    @Override
    public void activarMarca(Long marcaId) {
        Marca marca = getMarca(marcaId);
        marca.setEstado(Estado.ACTIVO);
        update(marca);
    }
}
