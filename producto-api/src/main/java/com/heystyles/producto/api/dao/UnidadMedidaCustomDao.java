package com.heystyles.producto.api.dao;

import com.heystyles.common.types.Page;
import com.heystyles.producto.api.entity.UnidadMedidaEntity;
import com.heystyles.producto.core.filter.UnidadMedidaFilter;

public interface UnidadMedidaCustomDao {

    Page<UnidadMedidaEntity> getPage(UnidadMedidaFilter filter);

}
