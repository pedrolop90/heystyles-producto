package com.heystyles.producto.core.dto;

import com.heystyles.common.types.ListResponse;
import com.heystyles.producto.core.domain.Marca;

import java.util.List;

public class MarcaListResponse extends ListResponse<Marca> {

    public MarcaListResponse() {
    }

    public MarcaListResponse(List<Marca> marcas) {
        super(marcas);
    }

    public MarcaListResponse(Long count, List<Marca> marcas) {
        super(marcas, count);
    }
}
