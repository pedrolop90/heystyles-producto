package com.heystyles.producto.api.dao;

import com.heystyles.producto.api.entity.MarcaEntity;

import java.util.List;

public interface MarcaProductoCustomDao {

    List<MarcaEntity> findMarcaByProductoId(Long productoId);

}
