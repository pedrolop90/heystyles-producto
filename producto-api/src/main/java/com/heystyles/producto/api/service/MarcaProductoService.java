package com.heystyles.producto.api.service;

import com.heystyles.producto.api.entity.MarcaEntity;

import java.util.List;

public interface MarcaProductoService {

    void upsert(Long productoId, List<Long> marcas);

    List<MarcaEntity> findMarcaByProductoId(Long productoId);
}
