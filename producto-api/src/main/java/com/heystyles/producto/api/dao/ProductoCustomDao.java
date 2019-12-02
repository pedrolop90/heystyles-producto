package com.heystyles.producto.api.dao;

import com.heystyles.common.types.Page;
import com.heystyles.producto.api.entity.ProductoEntity;
import com.heystyles.producto.core.filter.ProductoFilter;

public interface ProductoCustomDao {

    Page<ProductoEntity> getPage(ProductoFilter filter);

}
