package com.heystyles.producto.api.config;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.event.internal.jpa.CallbackRegistryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
public class HibernateListenerConfig {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private HibernateSoftDeleteListener softDeleteListener;

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.DELETE).clear(); // We need to replace the listener
        registry.getEventListenerGroup(EventType.DELETE).appendListener(softDeleteListener);
        softDeleteListener.injectCallbackRegistry(new CallbackRegistryImpl());
    }

}
