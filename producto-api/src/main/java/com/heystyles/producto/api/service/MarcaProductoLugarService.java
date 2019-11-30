package com.heystyles.producto.api.service;

import com.heystyles.producto.core.domain.Lugar;
import com.heystyles.producto.core.dto.MarcaProductoLugarRequest;

import java.util.List;

public interface MarcaProductoLugarService {

    void upsert(MarcaProductoLugarRequest request);

    List<Lugar> findLugaresByProductoIdAndMarcaId(Long productoId, Long marcaId);
}
