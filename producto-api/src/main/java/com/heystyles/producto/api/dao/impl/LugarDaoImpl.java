package com.heystyles.producto.api.dao.impl;

import com.heystyles.common.types.Page;
import com.heystyles.producto.api.dao.LugarCustomDao;
import com.heystyles.producto.api.entity.LugarEntity;
import com.heystyles.producto.core.filter.LugarFilter;
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
public class LugarDaoImpl implements LugarCustomDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<LugarEntity> getPage(LugarFilter filter) {
        Session session = entityManager.unwrap(org.hibernate.Session.class);

        Criteria totalCriteria = session.createCriteria(LugarEntity.class);

        Criteria pageCriteria = session.createCriteria(LugarEntity.class);

        applyWhere(totalCriteria, filter);
        applyWhere(pageCriteria, filter);

        applySort(totalCriteria, filter);
        applySort(pageCriteria, filter);

        queryCount(totalCriteria);
        queryPage(pageCriteria, filter.getPageNumber(), filter.getPageSize());

        Long total = (Long) totalCriteria.uniqueResult();
        List<LugarEntity> entities = pageCriteria.list();

        return new Page<>(total, entities);
    }

    private void applyWhere(Criteria criteria, LugarFilter filter) {
        if (filter.getEstado() != null) {
            criteria.add(Restrictions.eq(LugarEntity.Attributes.ESTADO, filter.getEstado()));
        }
        if (filter.getNombre() != null) {
            criteria.add(Restrictions.ilike(
                    LugarEntity.Attributes.NOMBRE, filter.getNombre(), MatchMode.ANYWHERE));
        }
        if (filter.getPosicionInicial() != null) {
            criteria.add(Restrictions.ge(LugarEntity.Attributes.POSICION, filter.getPosicionInicial()));
        }
        if (filter.getPosicionFinal() != null) {
            criteria.add(Restrictions.le(LugarEntity.Attributes.POSICION, filter.getPosicionFinal()));
        }
    }

    private void applySort(Criteria criteria, LugarFilter filter) {
        if (filter.getNombreAscending() != null) {
            if (filter.getNombreAscending()) {
                criteria.addOrder(Order.asc(LugarEntity.Attributes.NOMBRE));
            }
            else {
                criteria.addOrder(Order.desc(LugarEntity.Attributes.NOMBRE));
            }
        }
        if (filter.getEstadoAscending() != null) {
            if (filter.getEstadoAscending()) {
                criteria.addOrder(Order.asc(LugarEntity.Attributes.ESTADO));
            }
            else {
                criteria.addOrder(Order.desc(LugarEntity.Attributes.ESTADO));
            }
        }

        if (filter.getPosicionAscending() != null) {
            if (filter.getPosicionAscending()) {
                criteria.addOrder(Order.asc(LugarEntity.Attributes.POSICION));
            }
            else {
                criteria.addOrder(Order.desc(LugarEntity.Attributes.POSICION));
            }
        }
    }

    private void queryCount(Criteria criteria) {
        criteria.setProjection(Projections.count(LugarEntity.Attributes.ID));
    }

    private void queryPage(Criteria criteria, Integer pageNumber, Integer pageSize) {
        if (pageNumber != null && pageSize != null) {
            criteria.setFirstResult(pageNumber * pageSize);
            criteria.setMaxResults(pageSize);
        }
    }
}
