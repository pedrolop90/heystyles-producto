package com.heystyles.producto.core.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class LugarMarcaProductoRequest {

    private Long lugarId;

    @NotNull
    private List<Long> marcaProductosIds;

    public Long getLugarId() {
        return lugarId;
    }

    public void setLugarId(Long lugarId) {
        this.lugarId = lugarId;
    }

    public List<Long> getMarcaProductosIds() {
        return marcaProductosIds;
    }

    public void setMarcaProductosIds(List<Long> marcaProductosIds) {
        this.marcaProductosIds = marcaProductosIds;
    }
}
