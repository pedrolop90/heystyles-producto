package com.heystyles.producto.core.dto;

import com.heystyles.common.types.ListResponse;
import com.heystyles.producto.core.domain.Lugar;

import java.util.List;

public class LugarListResponse extends ListResponse<Lugar> {

    public LugarListResponse() {
    }

    public LugarListResponse(List<Lugar> lugares) {
        super(lugares);
    }

    public LugarListResponse(Long count, List<Lugar> lugares) {
        super(lugares, count);
    }
}
