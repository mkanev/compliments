package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.ContainingMediaEntity;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public class ContainingMediaEntityDAOImpl<T extends ContainingMediaEntity> extends GenericEntityDAOImpl<T> implements ContainingMediaEntityDAO<T> {

    public ContainingMediaEntityDAOImpl(Class<T> persistentClass) {
        super(persistentClass);
    }
}
