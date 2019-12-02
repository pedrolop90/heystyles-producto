package com.heystyles.producto.api.dao;

import com.heystyles.common.types.Page;
import com.heystyles.producto.api.entity.LugarEntity;
import com.heystyles.producto.core.filter.LugarFilter;

public interface LugarCustomDao {

    Page<LugarEntity> getPage(LugarFilter filter);

}
