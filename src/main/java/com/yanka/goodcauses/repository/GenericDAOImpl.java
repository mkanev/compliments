package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.common.LoggedClass;
import com.yanka.goodcauses.model.BaseDBObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Transactional(readOnly = true)
public class GenericDAOImpl<T extends BaseDBObject, PK extends Serializable> extends LoggedClass implements GenericDAO<T, PK> {

    @PersistenceContext
    protected EntityManager em;
    private Class<T> persistentClass;

    public GenericDAOImpl() {
    }

    public GenericDAOImpl(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getEntity(PK id) {
        if (id == null) {
            logWarning("Экземпляр класса %s не найден: ИД должен передаваться с запросом", persistentClass);
            return null;
        }
        T entity = em.find(persistentClass, id);
        if (entity == null) {
            logWarning("Экземпляр класса %s с ИД %d не найден", persistentClass, id);
            return null;
        }
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        T entity = em.find(this.persistentClass, id);
        return entity != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getExistingEntityList() {
        Query typedQuery = getExistingEntityQuery();
        return getResultList(typedQuery);
    }

    protected TypedQuery<T> getExistingEntityQuery() {
        CriteriaSet criteriaSet = getFilteredCriteriaSet();
        return em.createQuery(criteriaSet.cq);
    }

    @Override
    public long getExistingEntityCount() {
        CriteriaBuilder cb = GenericDAOImpl.this.em.getCriteriaBuilder();
        CriteriaQuery<Long> lq = cb.createQuery(Long.class);
        Root<T> r = lq.from(persistentClass);
        lq.select(cb.count(r));
        lq.where(cb.or(r.get(T.FIELD_DELETED).isNull(), cb.equal(r.get(T.FIELD_DELETED), Boolean.FALSE)));
        TypedQuery<Long> query = em.createQuery(lq);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        CriteriaSet criteriaSet = new CriteriaSet();
        TypedQuery typedQuery = em.createQuery(criteriaSet.cq);
        return getResultList(typedQuery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public T saveEntity(T entity) {
        if (entity == null) {
            logWarning("Невозможно сохранить null");
            return null;
        }
        return em.merge(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEntity(PK id) {
        T entity = getEntity(id);
        if (entity == null) {
            return;
        }
        deleteEntity(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEntity(T entity) {
        if (entity == null) {
            logWarning("Невозможно удалить null");
            return;
        }
        entity.setDeleted(true);
        entity.deleteFull();
        saveEntity(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void removeEntity(T entity) {
        if (entity == null) {
            logWarning("Невозможно удалить null");
            return;
        }
        em.remove(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(T obj) {
        if (obj == null) {
            logWarning("Невозможно заполнить null");
            return;
        }
        obj.loadFull();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getFull(T u) {
        fill(u);
        return u;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getFull(PK id) {
        T u = getEntity(id);
        fill(u);
        return u;
    }

    /**
     * Возвращает список сущностей, соответствующих запросу с параметрами
     *
     * @param query      экземпляр запроса
     * @param parameters параметры запроса (по порядку)
     */
    @SuppressWarnings("unchecked")
    protected List<T> getResultList(Query query, Object... parameters) {
        if (query == null) {
            logWarning("Экземпляр JPQL-запроса отсутствует");
            return null;
        }
        if (ArrayUtils.isNotEmpty(parameters)) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i + 1, parameters[i]);
            }
        }
        try {
            return (List<T>) query.getResultList();
        } catch (Exception ex) {
            logWarning("Ошибка выполнения запроса", ex);
            return null;
        }
    }

    /**
     * Возвращает сущность, соответствующую запросу с параметрами, с проверкой на единственность
     *
     * @param query      jpql-запрос
     * @param parameters параметры запроса
     * @param singleOnly флаг проверки на единственность
     */
    protected T getQueryResult(Query query, boolean singleOnly, Object... parameters) {
        List<T> resultList = getResultList(query, parameters);
        if (CollectionUtils.isEmpty(resultList)) {
            return null;
        }
        if (resultList.size() > 1) {
            logWarning("Запрос %s вернул множество результатов [количество: %d]", query, resultList.size());
            if (singleOnly) {
                return null;
            }
        }
        return resultList.iterator().next();
    }

    protected CriteriaSet getFilteredCriteriaSet() {
        CriteriaSet cs = new CriteriaSet();
        cs.filterDeleted();
        return cs;
    }

    /**
     * Класс для создания запросов к БД
     */
    protected class CriteriaSet {

        final CriteriaBuilder cb = GenericDAOImpl.this.em.getCriteriaBuilder();
        final CriteriaQuery<T> cq = cb.createQuery(persistentClass);
        final Root<T> r = cq.from(persistentClass);

        /**
         * Метод добавляет инструкции в <code>CriteriaQuery</code> для исключения из набора удаленных сущностей
         */
        protected void filterDeleted() {
            cq.where(getDeletedPredicate());
        }

        protected Predicate getDeletedPredicate() {
            return cb.or(r.get(T.FIELD_DELETED).isNull(), cb.equal(r.get(T.FIELD_DELETED), Boolean.FALSE));
        }

    }
}
