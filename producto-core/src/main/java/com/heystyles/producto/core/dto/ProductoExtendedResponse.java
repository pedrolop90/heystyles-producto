package com.heystyles.producto.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.producto.core.domain.ProductoExtended;

public class ProductoExtendedResponse extends ObjectResponse<ProductoExtended> {

    public ProductoExtendedResponse() {
    }

    public ProductoExtendedResponse(ProductoExtended productoExtended) {
        super(productoExtended);
    }
}
