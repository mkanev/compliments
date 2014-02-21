package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.Person;
import com.yanka.goodcauses.repository.GenericEntityDAO;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public class PersonManagerImpl<T extends Person> extends GenericEntityManagerImpl<T> implements PersonManager<T> {

    public PersonManagerImpl(GenericEntityDAO<T> genericDao) {
        super(genericDao);
    }
}
