package com.heystyles.producto.core.dto;

import com.heystyles.common.types.ListResponse;
import com.heystyles.producto.core.domain.ProductoExtended;

import java.util.List;

public class ProductoExtendedListResponse extends ListResponse<ProductoExtended> {

    public ProductoExtendedListResponse() {
    }

    public ProductoExtendedListResponse(List<ProductoExtended> productoExtendedList) {
        super(productoExtendedList);
    }
}
