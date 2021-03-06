package com.heystyles.producto.core.dto;

import com.heystyles.producto.core.domain.Producto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProductoRequest {

    @NotNull
    @Valid
    private Producto producto;

    private List<Long> marcas;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Long> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Long> marcas) {
        this.marcas = marcas;
    }
}
