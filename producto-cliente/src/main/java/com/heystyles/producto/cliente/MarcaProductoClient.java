package com.heystyles.producto.cliente;

import com.heystyles.producto.core.dto.MarcaProductoDto;

import java.util.List;

public interface MarcaProductoClient {

    List<MarcaProductoDto> getMarcasProductos();

    MarcaProductoDto findMarcaProductoById(Long marcaProductoId);

    List<MarcaProductoDto> findMarcaProductosById(List<Long> marcaProductosIds);
}
