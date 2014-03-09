package com.yanka.goodcauses.service;

import com.yanka.goodcauses.common.LoggedClass;
import com.yanka.goodcauses.model.GenericEntity;
import com.yanka.goodcauses.repository.GenericDAO;

import java.io.Serializable;
import java.util.List;


/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public abstract class GenericManagerImpl<T extends GenericEntity, PK extends Serializable> extends LoggedClass implements GenericManager<T, PK> {

    private static final long serialVersionUID = 4852856653776616598L;
    protected GenericDAO<T, PK> genericDAO;

    public GenericManagerImpl(GenericDAO<T, PK> genericDao) {
        this.genericDAO = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return genericDAO.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getExistingEntityList() {
        return genericDAO.getExistingEntityList();
    }

    @Override
    public long getExistingEntityCount() {
        return genericDAO.getExistingEntityCount();
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
        return genericDAO.getEntity(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        return genericDAO.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    public T save(T object) {
        logDebug("___GenericManagerImpl.save %s", object);
        return genericDAO.saveEntity(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        genericDAO.deleteEntity(id);
    }

    public T getFull(PK id) {
        return genericDAO.getFull(id);
    }

    @Override
    public T getRandomEntity() {
        return genericDAO.getRandomEntity();
    }
}

