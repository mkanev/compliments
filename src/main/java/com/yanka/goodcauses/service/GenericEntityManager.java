package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.GenericEntity;

import java.util.List;


/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public interface GenericEntityManager<T extends GenericEntity> extends GenericManager<T, Long> {

    List<T> getExistingEntityList();

    List<T> findByNamedQuery(String queryName, Object... parameters);

    List<T> findByNamedQuery(String queryName, int resultLimit, Object... parameters);

    List<T> findByNamedQuery(String queryName, int start, int end, Object... parameters);

    List<T> getDateTimeOrderedEntityList();

    List<T> getReverseDateTimeOrderedEntityList();

    List<T> getLatestEntityList(int size);

    T getRandomEntity();
}
