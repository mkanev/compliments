package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.Organization;
import com.yanka.goodcauses.repository.OrganizationDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Service
public class OrganizationManagerImpl extends ContainingMediaEntityManagerImpl<Organization> implements OrganizationManager {

    private OrganizationDAO organizationDao;

    @Autowired
    public OrganizationManagerImpl(OrganizationDAO organizationDao) {
        super(organizationDao);
        this.organizationDao = organizationDao;
    }

    public List<Organization> getPartnersList() {
        return organizationDao.getPartnersList();
    }

    public List<Organization> getFundsList() {
        return organizationDao.getFundsList();
    }
}
