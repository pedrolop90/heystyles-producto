package com.heystyles.producto.api.dao;

import com.heystyles.producto.api.entity.UnidadMedidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedidaDao
        extends JpaRepository<UnidadMedidaEntity, Long>, UnidadMedidaCustomDao {

    UnidadMedidaEntity findByNombre(String nombre);

}
