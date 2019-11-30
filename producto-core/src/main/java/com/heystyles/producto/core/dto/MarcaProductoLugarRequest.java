package com.heystyles.producto.core.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MarcaProductoLugarRequest {

    private Long marcaId;

    private Long productoId;

    @NotNull
    @Valid
    private List<Long> lugares;

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public List<Long> getLugares() {
        return lugares;
    }

    public void setLugares(List<Long> lugares) {
        this.lugares = lugares;
    }
}
