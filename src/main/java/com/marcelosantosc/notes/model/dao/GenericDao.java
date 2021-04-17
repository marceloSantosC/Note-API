package com.marcelosantosc.notes.model.dao;

import com.marcelosantosc.notes.utils.JpaUtil;
import lombok.Data;

import javax.persistence.EntityManager;

@Data
public abstract class GenericDao {

    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        if (this.entityManager == null || !this.entityManager.isOpen()) {
            this.setEntityManager(JpaUtil.getEntityManager());
        }
        return this.entityManager;
    }
}
