package com.heystyles.producto.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.producto.core.domain.Lugar;

public class LugarResponse extends ObjectResponse<Lugar> {

    public LugarResponse() {
    }

    public LugarResponse(Lugar lugar) {
        super(lugar);
    }
}
