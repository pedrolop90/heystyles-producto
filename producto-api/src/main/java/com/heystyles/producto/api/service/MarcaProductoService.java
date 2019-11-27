package com.heystyles.producto.api.service;

import com.heystyles.producto.api.entity.MarcaEntity;
import com.heystyles.producto.core.dto.MarcaProductoDto;

import java.util.List;

public interface MarcaProductoService {

    void upsert(Long productoId, List<Long> marcas);

    List<MarcaEntity> findMarcaByProductoId(Long productoId);

    List<MarcaProductoDto> getMarcaProductos();

    MarcaProductoDto getMarcaProductoById(Long marcaProductoId);

    List<MarcaProductoDto> getMarcaProductosById(List<Long> marcaProductoId);
}
