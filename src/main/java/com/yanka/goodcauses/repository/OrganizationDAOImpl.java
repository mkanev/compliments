package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.Organization;

import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Repository
public class OrganizationDAOImpl extends GenericEntityDAOImpl<Organization> implements OrganizationDAO {

    public OrganizationDAOImpl() {
        super(Organization.class);
    }
}
