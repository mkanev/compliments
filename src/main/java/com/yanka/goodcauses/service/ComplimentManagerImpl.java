package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.Compliment;
import com.yanka.goodcauses.repository.ComplimentDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Service
public class ComplimentManagerImpl extends GenericEntityManagerImpl<Compliment> implements ComplimentManager {

    private ComplimentDAO complimentDAO;

    @Autowired
    public ComplimentManagerImpl(ComplimentDAO complimentDAO) {
        super(complimentDAO);
        this.complimentDAO = complimentDAO;
    }
}
