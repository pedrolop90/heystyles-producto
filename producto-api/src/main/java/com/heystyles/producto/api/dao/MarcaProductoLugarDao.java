package com.heystyles.producto.api.dao;

import com.heystyles.producto.api.entity.MarcaProductoLugarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaProductoLugarDao
        extends JpaRepository<MarcaProductoLugarEntity, Long>, MarcaProductoLugarCustomDao {

    List<MarcaProductoLugarEntity> findByMarcaProductoId(Long marcaProductoId);

    List<MarcaProductoLugarEntity> findByLugarId(Long lugarId);

}
