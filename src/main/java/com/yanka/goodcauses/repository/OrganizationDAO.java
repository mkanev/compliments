package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.Organization;

import java.util.List;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public interface OrganizationDAO extends ContainingMediaEntityDAO<Organization> {

    List<Organization> getFundsList();

    List<Organization> getPartnersList();
}
