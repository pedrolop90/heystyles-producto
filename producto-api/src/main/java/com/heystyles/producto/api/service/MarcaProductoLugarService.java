package com.heystyles.producto.api.service;

import com.heystyles.producto.core.domain.Lugar;
import com.heystyles.producto.core.dto.LugarMarcaProductoRequest;
import com.heystyles.producto.core.dto.MarcaProductoDto;
import com.heystyles.producto.core.dto.MarcaProductoLugarExtendedRequest;
import com.heystyles.producto.core.dto.MarcaProductoLugarRequest;

import java.util.List;

public interface MarcaProductoLugarService {

    void upsert(MarcaProductoLugarRequest request);

    void upsert(MarcaProductoLugarExtendedRequest request);

    void upsertMarcaProductoByLugar(LugarMarcaProductoRequest request);

    List<Lugar> findLugaresByProductoIdAndMarcaId(Long productoId, Long marcaId);

    List<Lugar> findLugaresByProductoIdAndMarcaId(Long marcaProductoId);

    List<MarcaProductoDto> findMarcaProductoByLugar(Long lugarId);
}
