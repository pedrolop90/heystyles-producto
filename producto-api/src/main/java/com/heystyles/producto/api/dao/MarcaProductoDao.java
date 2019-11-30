package com.heystyles.producto.api.dao;

import com.heystyles.producto.api.entity.MarcaProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaProductoDao
        extends JpaRepository<MarcaProductoEntity, Long>, MarcaProductoCustomDao {

    List<MarcaProductoEntity> findByProductoId(Long productoId);

    List<MarcaProductoEntity> findByMarcaId(Long marcaId);

    MarcaProductoEntity findByProductoIdAndMarcaId(Long productoId, Long marcaId);
}
