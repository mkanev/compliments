package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.GenericEntity;
import com.yanka.goodcauses.repository.GenericEntityDAO;

import java.util.List;


/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public abstract class GenericEntityManagerImpl<T extends GenericEntity> extends GenericManagerImpl<T, Long> implements GenericEntityManager<T> {

    private GenericEntityDAO<T> genericEntityDao;

    public GenericEntityManagerImpl(GenericEntityDAO<T> genericEntityDao) {
        super(genericEntityDao);
        this.genericEntityDao = genericEntityDao;
    }

    @Override
    public List<T> findByNamedQuery(String queryName, Object... parameters) {
        return genericEntityDao.findByNamedQuery(queryName, parameters);
    }

    @Override
    public List<T> findByNamedQuery(String queryName, int resultLimit, Object... parameters) {
        return genericEntityDao.findByNamedQuery(queryName, resultLimit, parameters);
    }

    @Override
    public List<T> findByNamedQuery(String queryName, int start, int end, Object... parameters) {
        return genericEntityDao.findByNamedQuery(queryName, start, end, parameters);
    }

    @Override
    public List<T> getDateTimeOrderedEntityList() {
        return genericEntityDao.getDateTimeOrderedEntityList();
    }

    @Override
    public List<T> getReverseDateTimeOrderedEntityList() {
        return genericEntityDao.getReverseDateTimeOrderedEntityList();
    }

    @Override
    public List<T> getLatestEntityList(int size) {
        return genericEntityDao.getLatestEntityList(size);
    }

}
