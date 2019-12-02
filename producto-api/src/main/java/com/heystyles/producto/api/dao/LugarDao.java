package com.heystyles.producto.api.dao;

import com.heystyles.producto.api.entity.LugarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LugarDao
        extends JpaRepository<LugarEntity, Long>, LugarCustomDao {
}
