package com.heystyles.producto.api.service.impl;

import com.heystyles.common.service.ConverterService;
import com.heystyles.producto.api.dao.MarcaProductoDao;
import com.heystyles.producto.api.dao.MarcaProductoLugarDao;
import com.heystyles.producto.api.entity.LugarEntity;
import com.heystyles.producto.api.entity.MarcaProductoEntity;
import com.heystyles.producto.api.entity.MarcaProductoLugarEntity;
import com.heystyles.producto.api.service.MarcaProductoLugarService;
import com.heystyles.producto.core.domain.Lugar;
import com.heystyles.producto.core.dto.LugarMarcaProductoRequest;
import com.heystyles.producto.core.dto.MarcaProductoDto;
import com.heystyles.producto.core.dto.MarcaProductoLugarExtendedRequest;
import com.heystyles.producto.core.dto.MarcaProductoLugarRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MarcaProductoLugarServiceImpl implements MarcaProductoLugarService {

    @Autowired
    private MarcaProductoLugarDao marcaProductoLugarDao;

    @Autowired
    private MarcaProductoDao marcaProductoDao;

    @Autowired
    private ConverterService converterService;

    @Override
    public void upsert(MarcaProductoLugarRequest request) {
        Long productoId = request.getProductoId();
        Long marcaId = request.getMarcaId();
        List<Long> lugares = request.getLugares();
        if (lugares == null) {
            return;
        }

        List<MarcaProductoLugarEntity> existing = marcaProductoLugarDao.findByMarcaIdAndProductoId(marcaId, productoId);

        MarcaProductoEntity marcaProductoEntity = marcaProductoDao.findByProductoIdAndMarcaId(productoId, marcaId);

        upsertGeneral(existing, lugares, marcaProductoEntity);
    }

    @Override
    public void upsert(MarcaProductoLugarExtendedRequest request) {
        Long marcaProductoId = request.getMarcaProductoId();
        List<Long> lugares = request.getLugares();
        if (lugares == null) {
            return;
        }

        List<MarcaProductoLugarEntity> existing = marcaProductoLugarDao.findByMarcaProductoId(marcaProductoId);

        MarcaProductoEntity marcaProductoEntity = marcaProductoDao.findOne(marcaProductoId);

        upsertGeneral(existing, lugares, marcaProductoEntity);
    }

    @Override
    public void upsertMarcaProductoByLugar(LugarMarcaProductoRequest request) {
        Long lugarId = request.getLugarId();
        List<Long> marcaProductosIds = request.getMarcaProductosIds();
        if (marcaProductosIds == null) {
            return;
        }

        List<MarcaProductoLugarEntity> existing = marcaProductoLugarDao.findByLugarId(lugarId);

        List<MarcaProductoLugarEntity> toDelete = new ArrayList<>();
        List<MarcaProductoLugarEntity> toSave = new ArrayList<>();

        Set<Long> oldMarcasIds = existing
                .stream()
                .map(e -> e.getMarcaProducto().getId())
                .collect(Collectors.toSet());

        existing.stream()
                .filter(p -> !marcaProductosIds.contains(p.getMarcaProducto().getId()))
                .forEach(p -> toDelete.add(p));

        marcaProductosIds.stream()
                .filter(l -> !oldMarcasIds.contains(l))
                .forEach(l -> {
                    MarcaProductoLugarEntity entity = new MarcaProductoLugarEntity();
                    entity.setMarcaProducto(new MarcaProductoEntity(l));
                    entity.setLugar(new LugarEntity(lugarId));
                    toSave.add(entity);
                });

        marcaProductoLugarDao.delete(toDelete);
        marcaProductoLugarDao.save(toSave);
    }

    private void upsertGeneral(List<MarcaProductoLugarEntity> existing,
                               List<Long> lugares, MarcaProductoEntity marcaProductoEntity) {
        List<MarcaProductoLugarEntity> toDelete = new ArrayList<>();
        List<MarcaProductoLugarEntity> toSave = new ArrayList<>();

        Set<Long> oldMarcasIds = existing
                .stream()
                .map(e -> e.getLugar().getId())
                .collect(Collectors.toSet());

        existing.stream()
                .filter(p -> !lugares.contains(p.getLugar().getId()))
                .forEach(p -> toDelete.add(p));

        lugares.stream()
                .filter(l -> !oldMarcasIds.contains(l))
                .forEach(l -> {
                    MarcaProductoLugarEntity entity = new MarcaProductoLugarEntity();
                    entity.setMarcaProducto(marcaProductoEntity);
                    entity.setLugar(new LugarEntity(l));
                    toSave.add(entity);
                });

        marcaProductoLugarDao.delete(toDelete);
        marcaProductoLugarDao.save(toSave);
    }

    @Override
    public List<Lugar> findLugaresByProductoIdAndMarcaId(Long productoId, Long marcaId) {
        List<LugarEntity> lugarEntities = marcaProductoLugarDao.findLugarByMarcaIdAndProductoId(marcaId, productoId);
        return converterService.convertTo(lugarEntities, Lugar.class);
    }

    @Override
    public List<Lugar> findLugaresByProductoIdAndMarcaId(Long marcaProductoId) {
        List<LugarEntity> lugarEntities = marcaProductoLugarDao.findLugarByMarcaIdAndProductoId(marcaProductoId);
        return converterService.convertTo(lugarEntities, Lugar.class);
    }

    @Override
    public List<MarcaProductoDto> findMarcaProductoByLugar(Long lugarId) {
        List<MarcaProductoEntity> marcaProductoEntities = marcaProductoLugarDao.findMarcaProductoByLugarId(lugarId);
        return converterService.convertTo(marcaProductoEntities, MarcaProductoDto.class);
    }
}
