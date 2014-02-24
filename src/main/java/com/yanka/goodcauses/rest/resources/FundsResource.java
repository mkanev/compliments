package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.model.Organization;
import com.yanka.goodcauses.service.OrganizationManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Path;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Component
@Path("/funds")
public class FundsResource extends OrganizationResource {

    private OrganizationManager organizationManager;

    @Autowired
    public FundsResource(OrganizationManager organizationManager) {
        super(organizationManager);
        this.organizationManager = organizationManager;
    }

    @Override
    protected List<Organization> getEntityList() {
        return organizationManager.getFundsList();
    }
}
