package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.Contact;
import com.yanka.goodcauses.repository.ContactDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Service
public class ContactManagerImpl extends PersonManagerImpl<Contact> implements ContactManager {

    private ContactDAO contactDao;

    @Autowired
    public ContactManagerImpl(ContactDAO contactDao) {
        super(contactDao);
        this.contactDao = contactDao;
    }
}
