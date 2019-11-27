package com.heystyles.producto.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.producto.core.domain.Marca;

public class MarcaResponse extends ObjectResponse<Marca> {

    public MarcaResponse() {
    }

    public MarcaResponse(Marca marca) {
        super(marca);
    }
}
