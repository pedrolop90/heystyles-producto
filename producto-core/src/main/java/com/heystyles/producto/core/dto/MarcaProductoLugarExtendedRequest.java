package com.heystyles.producto.core.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MarcaProductoLugarExtendedRequest {

    @NotNull
    private Long marcaProductoId;

    @NotNull
    @Valid
    private List<Long> lugares;

    public Long getMarcaProductoId() {
        return marcaProductoId;
    }

    public void setMarcaProductoId(Long marcaProductoId) {
        this.marcaProductoId = marcaProductoId;
    }

    public List<Long> getLugares() {
        return lugares;
    }

    public void setLugares(List<Long> lugares) {
        this.lugares = lugares;
    }
}
