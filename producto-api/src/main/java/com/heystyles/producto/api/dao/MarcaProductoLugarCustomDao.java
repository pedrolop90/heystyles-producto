package com.heystyles.producto.api.dao;

import com.heystyles.producto.api.entity.LugarEntity;
import com.heystyles.producto.api.entity.MarcaProductoLugarEntity;
import java.util.List;

public interface MarcaProductoLugarCustomDao {

    List<MarcaProductoLugarEntity> findByMarcaIdAndProductoId(Long marcaId, Long productoId);

    List<LugarEntity> findLugarByMarcaIdAndProductoId(Long marcaId, Long productoId);

    List<LugarEntity> findLugarByMarcaIdAndProductoId(Long marcaProductoId);
}
