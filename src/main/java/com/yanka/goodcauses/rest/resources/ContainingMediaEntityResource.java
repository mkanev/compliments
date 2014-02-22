package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.model.ContainingMediaEntity;
import com.yanka.goodcauses.service.ContainingMediaEntityManager;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public abstract class ContainingMediaEntityResource<T extends ContainingMediaEntity> extends GenericEntityResource<T> {

    private ContainingMediaEntityManager<T> containingMediaEntityManager;

    public ContainingMediaEntityResource(ContainingMediaEntityManager<T> containingMediaEntityManager) {
        super(containingMediaEntityManager);
        this.containingMediaEntityManager = containingMediaEntityManager;
    }
}
