package com.heystyles.producto.api.dao;

import com.heystyles.producto.api.entity.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaDao
        extends JpaRepository<MarcaEntity, Long>, MarcaCustomDao {

    MarcaEntity findByNombre(String nombre);

}
