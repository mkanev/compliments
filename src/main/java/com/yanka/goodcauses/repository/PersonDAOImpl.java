package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.Person;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public class PersonDAOImpl<T extends Person> extends GenericEntityDAOImpl<T> implements PersonDAO<T> {

    public PersonDAOImpl() {
    }

    public PersonDAOImpl(Class<T> persistentClass) {
        super(persistentClass);
    }
}
