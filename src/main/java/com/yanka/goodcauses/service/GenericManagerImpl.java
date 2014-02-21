package com.yanka.goodcauses.service;

import com.yanka.goodcauses.common.LoggedClass;
import com.yanka.goodcauses.model.GenericEntity;
import com.yanka.goodcauses.repository.GenericDAO;
import com.yanka.goodcauses.repository.GenericEntityDAO;

import java.io.Serializable;
import java.util.List;


/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public abstract class GenericManagerImpl<T extends GenericEntity, PK extends Serializable> extends LoggedClass implements GenericManager<T, PK> {

    private static final long serialVersionUID = 4852856653776616598L;
    protected GenericDAO<T, PK> dao;

    public GenericManagerImpl(GenericDAO<T, PK> genericDao) {
        this.dao = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
        return dao.getEntity(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        return dao.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    public T save(T object) {
        logDebug("___GenericManagerImpl.save %s", object);
        return dao.saveEntity(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        dao.deleteEntity(id);
    }

    public T getFull(PK id) {
        return dao.getFull(id);
    }
}

