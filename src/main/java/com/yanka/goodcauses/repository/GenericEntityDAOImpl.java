package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.GenericEntity;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * {@inheritDoc}
 */
@Transactional(readOnly = true)
public abstract class GenericEntityDAOImpl<T extends GenericEntity> extends GenericDAOImpl<T, Long> implements GenericEntityDAO<T> {

    protected GenericEntityDAOImpl() {
    }

    protected GenericEntityDAOImpl(Class<T> persistentClass) {
        super(persistentClass);
    }

    /**
     * Возвращает список сущностей, соответствующих именованому запросу с параметрами
     *
     * @param queryName  имя запроса
     * @param parameters параметры
     */
    public List<T> findByNamedQuery(String queryName, Object... parameters) {
        return findByNamedQuery(queryName, 0, 0, parameters);
    }

    /**
     * Возвращает список сущностей, соответствующих именованому запросу с параметрами
     *
     * @param queryName   имя запроса
     * @param resultLimit ограничение по количеству результатов
     * @param parameters  параметры
     */
    public List<T> findByNamedQuery(String queryName, int resultLimit, Object... parameters) {
        return findByNamedQuery(queryName, 0, resultLimit, parameters);
    }

    /**
     * Возвращает список сущностей, соответствующих именованому запросу с параметрами
     *
     * @param queryName  имя запроса
     * @param start      номер от
     * @param end        номер до
     * @param parameters параметры
     */
    public List<T> findByNamedQuery(String queryName, int start, int end, Object... parameters) {
        if (StringUtils.isBlank(queryName)) {
            logWarning("queryName не может быть null");
            return null;
        }
        if (start < 0) {
            throw new IllegalArgumentException("start should be greater than 0");
        }
        int pageSize = end - start;
        if (pageSize < 0) {
            throw new IllegalArgumentException("end should be greater than start");
        }
        Query query = em.createNamedQuery(queryName);
        if (start > 0) {
            query.setFirstResult(start);
        }
        if (pageSize > 0) {
            query.setMaxResults(pageSize);
        }
        return getResultList(query, parameters);
    }

    @Override
    public List<T> getDateTimeOrderedEntityList() {
        CriteriaSet cs = getFilteredCriteriaSet();
        cs.cq.orderBy(cs.cb.asc(cs.r.get(T.FIELD_UPDATE_DATE)));
        TypedQuery typedQuery = em.createQuery(cs.cq);
        return getResultList(typedQuery);
    }

    @Override
    public List<T> getReverseDateTimeOrderedEntityList() {
        CriteriaSet cs = getFilteredCriteriaSet();
        cs.cq.orderBy(cs.cb.desc(cs.r.get(T.FIELD_UPDATE_DATE)));
        TypedQuery typedQuery = em.createQuery(cs.cq);
        return getResultList(typedQuery);
    }

    @Override
    public T getRandomEntity() {
        Long entityCount = getExistingEntityCount();
        Random random = new Random();
        TypedQuery<T> existingEntityQuery = getExistingEntityQuery();
        existingEntityQuery.setFirstResult(random.nextInt(entityCount.intValue()));
        existingEntityQuery.setMaxResults(1);
        return existingEntityQuery.getSingleResult();
    }

    @Override
    public List<T> getLatestEntityList(int size) {
        List<T> entityList = getReverseDateTimeOrderedEntityList();
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        return entityList.subList(0, Math.max(0, Math.min(entityList.size(), size)));
    }

    /**
     * Возвращает сущность, соответствующую запросу с параметрами, с проверкой на единственность
     *
     * @param queryName  имя запроса
     * @param parameters параметры запроса
     * @param singleOnly флаг проверки на единственность
     */
    protected T getNamedQueryResult(String queryName, boolean singleOnly, Object... parameters) {
        List<T> resultList = findByNamedQuery(queryName, parameters);
        if (CollectionUtils.isEmpty(resultList)) {
            logDebug("Запрос %s не вернул результатов", queryName);
            return null;
        }
        if (resultList.size() > 1) {
            logWarning("Запрос %s вернул множество результатов [количество: %d]", queryName, resultList.size());
            if (singleOnly) {
                return null;
            }
        }
        return resultList.iterator().next();
    }

}