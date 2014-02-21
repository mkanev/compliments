package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.GenericEntity;
import com.yanka.goodcauses.repository.GenericEntityDAO;

import java.util.List;


/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public abstract class GenericEntityManagerImpl<T extends GenericEntity> extends GenericManagerImpl<T, Long> implements GenericEntityManager<T> {

    private GenericEntityDAO<T> genericDao;

    public GenericEntityManagerImpl(GenericEntityDAO<T> genericDao) {
        super(genericDao);
        this.genericDao = genericDao;
    }

    @Override
    public List<T> getExistingEntityList() {
        return genericDao.getExistingEntityList();
    }

    @Override
    public List<T> findByNamedQuery(String queryName, Object... parameters) {
        return genericDao.findByNamedQuery(queryName, parameters);
    }

    @Override
    public List<T> findByNamedQuery(String queryName, int resultLimit, Object... parameters) {
        return genericDao.findByNamedQuery(queryName, resultLimit, parameters);
    }

    @Override
    public List<T> findByNamedQuery(String queryName, int start, int end, Object... parameters) {
        return genericDao.findByNamedQuery(queryName, start, end, parameters);
    }
}
