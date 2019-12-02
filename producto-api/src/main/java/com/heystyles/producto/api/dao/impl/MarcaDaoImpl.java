package com.heystyles.producto.api.dao.impl;

import com.heystyles.common.types.Page;
import com.heystyles.producto.api.dao.MarcaCustomDao;
import com.heystyles.producto.api.entity.MarcaEntity;
import com.heystyles.producto.core.filter.MarcaFilter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional(readOnly = true)
public class MarcaDaoImpl implements MarcaCustomDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Page<MarcaEntity> getPage(MarcaFilter filter) {
        Session session = entityManager.unwrap(org.hibernate.Session.class);

        Criteria totalCriteria = session.createCriteria(MarcaEntity.class);

        Criteria pageCriteria = session.createCriteria(MarcaEntity.class);

        applyWhere(totalCriteria, filter);
        applyWhere(pageCriteria, filter);

        applySort(totalCriteria, filter);
        applySort(pageCriteria, filter);

        queryCount(totalCriteria);
        queryPage(pageCriteria, filter.getPageNumber(), filter.getPageSize());

        Long total = (Long) totalCriteria.uniqueResult();
        List<MarcaEntity> entities = pageCriteria.list();

        return new Page<>(total, entities);
    }

    private void applyWhere(Criteria criteria, MarcaFilter filter) {
        if (filter.getEstado() != null) {
            criteria.add(Restrictions.eq(MarcaEntity.Attributes.ESTADO, filter.getEstado()));
        }
        if (filter.getNombre() != null) {
            criteria.add(Restrictions.ilike(
                    MarcaEntity.Attributes.NOMBRE, filter.getNombre(), MatchMode.ANYWHERE));
        }
    }

    private void applySort(Criteria criteria, MarcaFilter filter) {
        if (filter.getNombreAscending() != null) {
            if (filter.getNombreAscending()) {
                criteria.addOrder(Order.asc(MarcaEntity.Attributes.NOMBRE));
            }
            else {
                criteria.addOrder(Order.desc(MarcaEntity.Attributes.NOMBRE));
            }
        }
        if (filter.getEstadoAscending() != null) {
            if (filter.getEstadoAscending()) {
                criteria.addOrder(Order.asc(MarcaEntity.Attributes.ESTADO));
            }
            else {
                criteria.addOrder(Order.desc(MarcaEntity.Attributes.ESTADO));
            }
        }
    }

    private void queryCount(Criteria criteria) {
        criteria.setProjection(Projections.count(MarcaEntity.Attributes.ID));
    }

    private void queryPage(Criteria criteria, Integer pageNumber, Integer pageSize) {
        if (pageNumber != null && pageSize != null) {
            criteria.setFirstResult(pageNumber * pageSize);
            criteria.setMaxResults(pageSize);
        }
    }
}
