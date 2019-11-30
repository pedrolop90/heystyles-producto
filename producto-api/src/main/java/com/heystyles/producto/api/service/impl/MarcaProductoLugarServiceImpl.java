package com.heystyles.producto.api.service.impl;

import com.heystyles.common.service.ConverterService;
import com.heystyles.producto.api.dao.MarcaProductoDao;
import com.heystyles.producto.api.dao.MarcaProductoLugarDao;
import com.heystyles.producto.api.entity.LugarEntity;
import com.heystyles.producto.api.entity.MarcaProductoEntity;
import com.heystyles.producto.api.entity.MarcaProductoLugarEntity;
import com.heystyles.producto.api.service.MarcaProductoLugarService;
import com.heystyles.producto.core.domain.Lugar;
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


}
