package com.heystyles.producto.api.dao;

import com.heystyles.producto.api.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoDao
        extends JpaRepository<ProductoEntity, Long>, ProductoCustomDao {

    ProductoEntity findByNombre(String nombre);

    List<ProductoEntity> findByUnidadMedidaId(Long unidadMedidaId);
}
