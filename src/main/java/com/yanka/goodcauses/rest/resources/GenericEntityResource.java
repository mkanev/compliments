package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.model.GenericEntity;
import com.yanka.goodcauses.service.GenericEntityManager;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public abstract class GenericEntityResource<T extends GenericEntity> extends GenericResource<T, Long> {

    private GenericEntityManager<T> genericEntityManager;

    public GenericEntityResource(GenericEntityManager<T> genericEntityManager) {
        super(genericEntityManager);
        this.genericEntityManager = genericEntityManager;
    }
}
