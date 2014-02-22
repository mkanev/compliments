package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.ContainingMediaEntity;
import com.yanka.goodcauses.repository.ContainingMediaEntityDAO;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public abstract class ContainingMediaEntityManagerImpl<T extends ContainingMediaEntity> extends GenericEntityManagerImpl<T> implements ContainingMediaEntityManager<T> {

    private ContainingMediaEntityDAO<T> containingMediaEntityDAO;

    public ContainingMediaEntityManagerImpl(ContainingMediaEntityDAO<T> containingMediaEntityDAO) {
        super(containingMediaEntityDAO);
        this.containingMediaEntityDAO = containingMediaEntityDAO;
    }
}
