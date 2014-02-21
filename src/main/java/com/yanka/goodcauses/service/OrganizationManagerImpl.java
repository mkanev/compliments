package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.Organization;
import com.yanka.goodcauses.repository.OrganizationDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Service
public class OrganizationManagerImpl extends GenericEntityManagerImpl<Organization> implements OrganizationManager {

    private OrganizationDAO organizationDao;

    @Autowired
    public OrganizationManagerImpl(OrganizationDAO organizationDao) {
        super(organizationDao);
        this.organizationDao = organizationDao;
    }
}
