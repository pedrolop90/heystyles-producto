package com.heystyles.producto.api.dao;

import com.heystyles.producto.api.entity.MarcaEntity;
import com.heystyles.producto.api.entity.MarcaProductoEntity;

import java.util.Collection;
import java.util.List;

public interface MarcaProductoCustomDao {

    List<MarcaEntity> findMarcaByProductoId(Long productoId);

    List<MarcaProductoEntity> findEagerAllMarcaProducto();

    List<MarcaProductoEntity> findEagerMarcaProductosById(Collection marcaProductosIds);

}
