package com.accountmasivebackend.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

/**
 *
 * @author DavidPro
 */
public class GenericDAO<T> {

    @PersistenceContext(unitName = "my_persistence_unit")
    protected EntityManager em;

    private Class<T> instance;

    public GenericDAO() {
    }


    public GenericDAO(Class<T> instance) {
        this.instance = instance;
    }

    public void insert(T instance) {
        em.persist(instance);
        em.flush();
    }

    public void insertList(List<T> instanceList) {
        instanceList.forEach(item -> {
            em.persist(item);
        });
        em.flush();
    }

    public void remove(T instance) {
        em.remove(em.merge(instance));
        em.flush();
    }

    public void update(T instance) {
        em.merge(instance);
        em.flush();
    }

    public void updateList(List<T> instanceList) {
        instanceList.forEach(item -> {
            em.merge(item);
        });
        em.flush();
    }

    public void deleteList(List<T> instanceList) {
        instanceList.forEach(item -> {
            em.remove(em.merge(item));
        });
        em.flush();
    }

    public void flush() {
        em.flush();
    }
}
