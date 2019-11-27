package com.heystyles.producto.api.dao.impl;

import com.heystyles.producto.api.dao.MarcaProductoCustomDao;
import com.heystyles.producto.api.entity.MarcaEntity;
import com.heystyles.producto.api.entity.MarcaProductoEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional(readOnly = true)
public class MarcaProductoDaoImpl implements MarcaProductoCustomDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MarcaEntity> findMarcaByProductoId(Long productoId) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(MarcaProductoEntity.class);
        criteria.add(Restrictions.eq(MarcaProductoEntity.Attributes.PRODUCTO_ID, productoId));
        criteria.setProjection(Projections.property(MarcaProductoEntity.Attributes.MARCA));
        return criteria.list();
    }
}
