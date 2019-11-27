package com.heystyles.producto.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.producto.core.domain.UnidadMedida;

public class UnidadMedidaResponse extends ObjectResponse<UnidadMedida> {

    public UnidadMedidaResponse() {
    }

    public UnidadMedidaResponse(UnidadMedida unidadMedida) {
        super(unidadMedida);
    }
}
