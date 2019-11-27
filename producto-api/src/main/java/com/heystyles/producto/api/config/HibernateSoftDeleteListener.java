package com.heystyles.producto.api.config;

import com.heystyles.common.types.SoftDeletable;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.DeleteEvent;
import org.hibernate.jpa.event.internal.core.JpaDeleteEventListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class HibernateSoftDeleteListener extends JpaDeleteEventListener {

    @Override
    public void onDelete(DeleteEvent event, Set transientEntities) throws HibernateException {
        if (event.getObject() instanceof SoftDeletable) {
            SoftDeletable obj = (SoftDeletable) event.getObject();
            obj.markAsDeleted();
            event.getSession().merge(obj);
            return;
        }
        super.onDelete(event, transientEntities);
    }

}
