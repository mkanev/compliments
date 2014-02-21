package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.Contact;

import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Repository
public class ContactDAOImpl extends PersonDAOImpl<Contact> implements ContactDAO {

    public ContactDAOImpl() {
        super(Contact.class);
    }

}
