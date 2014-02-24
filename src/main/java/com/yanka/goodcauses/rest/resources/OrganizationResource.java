package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.model.Organization;
import com.yanka.goodcauses.service.OrganizationManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public abstract class OrganizationResource extends ContainingMediaEntityResource<Organization> {

    private OrganizationManager organizationManager;

    public OrganizationResource(OrganizationManager organizationManager) {
        super(organizationManager);
        this.organizationManager = organizationManager;
    }

    /*@Path("/funds")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String fundsList() throws IOException {
        logDebug("fundsList()");
        return getPreviewWriter().writeValueAsString(organizationManager.getFundsList());
    }*/

    /*@Path("/partners")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String partnersList() throws IOException {
        logDebug("partnersList()");
        return getPreviewWriter().writeValueAsString(organizationManager.getPartnersList());
    }*/

}
