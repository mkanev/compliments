package com.yanka.goodcauses.service;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */

import com.yanka.goodcauses.model.BaseDBObject;

import java.io.Serializable;
import java.util.List;


public interface GenericManager<T extends BaseDBObject, PK extends Serializable> {

    /**
     * Generic method used to get all objects of a particular type. This is the same as lookup up all rows in a table.
     *
     * @return List of populated objects
     */
    List<T> getAll();

    /**
     * Общий метод для получения сущностей одного типа, которые не были удалены.
     *
     * @return Список полученных объектов
     */
    List<T> getExistingEntityList();

    /**
     * Общий метод для получения количества сущностей одного типа, которые не были удалены.
     *
     * @return Список полученных объектов
     */
    long getExistingEntityCount();

    /**
     * Generic method to get an object based on class and identifier. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
     *
     * @param id the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);

    /**
     * Checks for existence of an object of type T using the id arg.
     *
     * @param id the identifier (primary key) of the object to get
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);

    /**
     * Generic method to save an object - handles both update and insert.
     *
     * @param object the object to save
     * @return the updated object
     */
    T save(T object);

    /**
     * Generic method to delete an object based on class and id
     *
     * @param id the identifier (primary key) of the object to remove
     */
    void remove(PK id);

    T getFull(PK id);
}
