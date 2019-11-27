package com.heystyles.producto.core.dto;

import com.heystyles.common.types.ListResponse;
import com.heystyles.producto.core.domain.UnidadMedida;

import java.util.List;

public class UnidadMedidaListResponse extends ListResponse<UnidadMedida> {

    public UnidadMedidaListResponse() {
    }

    public UnidadMedidaListResponse(List<UnidadMedida> unidadMedidaList) {
        super(unidadMedidaList);
    }
}
