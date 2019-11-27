package com.heystyles.producto.core.dto;

import com.heystyles.common.types.ObjectResponse;

public class MarcaProductoResponse extends ObjectResponse<MarcaProductoDto> {

    public MarcaProductoResponse() {
    }

    public MarcaProductoResponse(MarcaProductoDto marcaProductoDto) {
        super(marcaProductoDto);
    }
}
