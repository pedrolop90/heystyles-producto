package com.heystyles.producto.api.dao.impl;

import com.heystyles.producto.api.dao.MarcaProductoCustomDao;
import com.heystyles.producto.api.entity.MarcaEntity;
import com.heystyles.producto.api.entity.MarcaProductoEntity;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
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

    @Override
    public List<MarcaProductoEntity> findEagerAllMarcaProducto() {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(MarcaProductoEntity.class);
        criteria.setFetchMode(MarcaProductoEntity.Attributes.MARCA, FetchMode.EAGER);
        criteria.setFetchMode(MarcaProductoEntity.Attributes.PRODUCTO, FetchMode.EAGER);
        return criteria.list();
    }

    @Override
    public List<MarcaProductoEntity> findEagerMarcaProductosById(Collection marcaProductosIds) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(MarcaProductoEntity.class);
        criteria.setFetchMode(MarcaProductoEntity.Attributes.MARCA, FetchMode.EAGER);
        criteria.setFetchMode(MarcaProductoEntity.Attributes.PRODUCTO, FetchMode.EAGER);
        if (marcaProductosIds != null && !marcaProductosIds.isEmpty()) {
            criteria.add(Restrictions.in(MarcaProductoEntity.Attributes.ID, marcaProductosIds));
        }
        else {
            criteria.add(Restrictions.not(
                    Restrictions.eqProperty(MarcaProductoEntity.Attributes.ID, MarcaProductoEntity.Attributes.ID)));
        }
        return criteria.list();
    }
}
