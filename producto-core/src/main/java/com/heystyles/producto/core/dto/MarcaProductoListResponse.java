package com.heystyles.producto.core.dto;

import com.heystyles.common.types.ListResponse;

import java.util.List;

public class MarcaProductoListResponse extends ListResponse<MarcaProductoDto> {

    public MarcaProductoListResponse() {
    }

    public MarcaProductoListResponse(List<MarcaProductoDto> marcaProductoDtoList) {
        super(marcaProductoDtoList);
    }
}
