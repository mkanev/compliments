package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.Compliment;

import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Repository
public class ComplimentDAOImpl extends GenericEntityDAOImpl<Compliment> implements ComplimentDAO {

    public ComplimentDAOImpl() {
        super(Compliment.class);
    }
}
