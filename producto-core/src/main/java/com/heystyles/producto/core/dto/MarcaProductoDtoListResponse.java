package com.heystyles.producto.core.dto;

import com.heystyles.common.types.ListResponse;

import java.util.List;

public class MarcaProductoDtoListResponse extends ListResponse<MarcaProductoDto> {

    public MarcaProductoDtoListResponse() {
    }

    public MarcaProductoDtoListResponse(List<MarcaProductoDto> marcaProductoDtoList) {
        super(marcaProductoDtoList);
    }
}
