package com.heystyles.producto.api.dao;

import com.heystyles.producto.api.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDao extends JpaRepository<ProductoEntity, Long> {
}
