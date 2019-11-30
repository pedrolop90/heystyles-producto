package com.heystyles.producto.api.dao.impl;

import com.heystyles.producto.api.dao.MarcaProductoLugarCustomDao;
import com.heystyles.producto.api.entity.LugarEntity;
import com.heystyles.producto.api.entity.MarcaProductoLugarEntity;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional(readOnly = true)
public class MarcaProductoLugarDaoImpl implements MarcaProductoLugarCustomDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MarcaProductoLugarEntity> findByMarcaIdAndProductoId(Long marcaId, Long productoId) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(MarcaProductoLugarEntity.class);
        criteria.createAlias(MarcaProductoLugarEntity.Attributes.MARCA_PRODUCTO,
                MarcaProductoLugarEntity.Attributes.MARCA_PRODUCTO);
        criteria.add(Restrictions.eq(MarcaProductoLugarEntity.Attributes.MARCA_PRODUCTO_MARCA_ID, marcaId));
        criteria.add(Restrictions.eq(MarcaProductoLugarEntity.Attributes.MARCA_PRODUCTO_PRODUCTO_ID, productoId));
        criteria.setFetchMode(MarcaProductoLugarEntity.Attributes.MARCA_PRODUCTO, FetchMode.EAGER);
        criteria.setFetchMode(MarcaProductoLugarEntity.Attributes.MARCA_PRODUCTO_MARCA, FetchMode.EAGER);
        criteria.setFetchMode(MarcaProductoLugarEntity.Attributes.MARCA_PRODUCTO_PRODUCTO, FetchMode.EAGER);
        criteria.setFetchMode(MarcaProductoLugarEntity.Attributes.LUGAR, FetchMode.EAGER);
        return criteria.list();
    }

    @Override
    public List<LugarEntity> findLugarByMarcaIdAndProductoId(Long marcaId, Long productoId) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(MarcaProductoLugarEntity.class);
        criteria.createAlias(MarcaProductoLugarEntity.Attributes.MARCA_PRODUCTO,
                MarcaProductoLugarEntity.Attributes.MARCA_PRODUCTO);
        criteria.add(Restrictions.eq(MarcaProductoLugarEntity.Attributes.MARCA_PRODUCTO_MARCA_ID, marcaId));
        criteria.add(Restrictions.eq(MarcaProductoLugarEntity.Attributes.MARCA_PRODUCTO_PRODUCTO_ID, productoId));
        criteria.setProjection(Projections.property(MarcaProductoLugarEntity.Attributes.LUGAR));
        return criteria.list();
    }
}
