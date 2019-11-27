package com.heystyles.producto.api.service.impl;

import com.heystyles.common.service.ConverterService;
import com.heystyles.producto.api.dao.MarcaProductoDao;
import com.heystyles.producto.api.entity.MarcaEntity;
import com.heystyles.producto.api.entity.MarcaProductoEntity;
import com.heystyles.producto.api.entity.ProductoEntity;
import com.heystyles.producto.api.service.MarcaProductoService;
import com.heystyles.producto.core.dto.MarcaProductoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MarcaProductoServiceImpl implements MarcaProductoService {

    @Autowired
    private MarcaProductoDao marcaProductoDao;

    @Autowired
    private ConverterService converterService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void upsert(Long productoId, List<Long> marcas) {
        if (marcas == null) {
            return;
        }
        List<MarcaProductoEntity> existing = marcaProductoDao.findByProductoId(productoId);

        List<MarcaProductoEntity> toDelete = new ArrayList<>();
        List<MarcaProductoEntity> toSave = new ArrayList<>();

        Set<Long> oldMarcasIds = existing
                .stream()
                .map(e -> e.getMarca().getId())
                .collect(Collectors.toSet());

        existing.stream()
                .filter(p -> !marcas.contains(p.getMarca().getId()))
                .forEach(p -> toDelete.add(p));

        marcas.stream()
                .filter(l -> !oldMarcasIds.contains(l))
                .forEach(l -> {
                    MarcaProductoEntity entity = new MarcaProductoEntity();
                    entity.setProducto(new ProductoEntity(productoId));
                    entity.setMarca(new MarcaEntity(l));
                    toSave.add(entity);
                });

        marcaProductoDao.delete(toDelete);
        marcaProductoDao.save(toSave);
    }

    @Override
    public List<MarcaEntity> findMarcaByProductoId(Long productoId) {
        return marcaProductoDao.findMarcaByProductoId(productoId);
    }

    @Override
    public List<MarcaProductoDto> getMarcaProductos() {
        List<MarcaProductoEntity> marcaProductoEntities = marcaProductoDao.findEagerAllMarcaProducto();
        return converterService.convertTo(marcaProductoEntities, MarcaProductoDto.class);
    }

    @Override
    public MarcaProductoDto getMarcaProductoById(Long marcaProductoId) {
        List<MarcaProductoEntity> entities = marcaProductoDao.findEagerMarcaProductosById(Arrays.asList(marcaProductoId));
        if (entities.size() > 0) {
            MarcaProductoEntity marcaProductoEntity = entities.get(0);
            return converterService.convertTo(marcaProductoEntity, MarcaProductoDto.class);
        }
        return null;
    }

    @Override
    public List<MarcaProductoDto> getMarcaProductosById(List<Long> marcaProductoId) {
        List<MarcaProductoEntity> entities = marcaProductoDao.findEagerMarcaProductosById(marcaProductoId);
        return converterService.convertTo(entities, MarcaProductoDto.class);
    }
}
