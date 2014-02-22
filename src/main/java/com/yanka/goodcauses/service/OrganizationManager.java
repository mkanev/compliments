package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.Organization;

import java.util.List;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public interface OrganizationManager extends ContainingMediaEntityManager<Organization> {

    public List<Organization> getPartnersList();

    public List<Organization> getFundsList();

}
