package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.Organization;
import com.yanka.goodcauses.model.OrganizationType;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.TypedQuery;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Repository
public class OrganizationDAOImpl extends ContainingMediaEntityDAOImpl<Organization> implements OrganizationDAO {

    public OrganizationDAOImpl() {
        super(Organization.class);
    }

    @Override
    public List<Organization> getFundsList() {
        CriteriaSet cs = new CriteriaSet();
        cs.filterDeleted();
        cs.cq.where(cs.cb.equal(cs.r.get(Organization.FIELD_ORGANIZATION_TYPE), OrganizationType.FUND));
        TypedQuery typedQuery = em.createQuery(cs.cq);
        return getResultList(typedQuery);
    }

    @Override
    public List<Organization> getPartnersList() {
        CriteriaSet cs = new CriteriaSet();
        cs.filterDeleted();
        cs.cq.where(cs.cb.equal(cs.r.get(Organization.FIELD_ORGANIZATION_TYPE), OrganizationType.PARTNER));
        TypedQuery typedQuery = em.createQuery(cs.cq);
        return getResultList(typedQuery);
    }
}
