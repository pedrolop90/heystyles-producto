package com.heystyles.producto.core.dto;

import com.heystyles.producto.core.domain.Marca;
import com.heystyles.producto.core.domain.ProductoExtended;

public class MarcaProductoDto {

    private Long id;

    private ProductoExtended producto;

    private Marca marca;

    private Long stockMinimo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductoExtended getProducto() {
        return producto;
    }

    public void setProducto(ProductoExtended producto) {
        this.producto = producto;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Long getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Long stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}
