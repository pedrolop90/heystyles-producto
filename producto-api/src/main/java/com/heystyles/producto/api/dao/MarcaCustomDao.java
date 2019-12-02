package com.heystyles.producto.api.dao;

import com.heystyles.common.types.Page;
import com.heystyles.producto.api.entity.MarcaEntity;
import com.heystyles.producto.core.filter.MarcaFilter;

public interface MarcaCustomDao {

    Page<MarcaEntity> getPage(MarcaFilter filter);

}
