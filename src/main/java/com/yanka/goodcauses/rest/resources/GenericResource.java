package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.JsonViews;
import com.yanka.goodcauses.common.LoggedClass;
import com.yanka.goodcauses.model.BaseDBObject;
import com.yanka.goodcauses.service.GenericManager;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public abstract class GenericResource<T extends BaseDBObject, PK extends Long> extends LoggedClass {

    @Autowired
    private ObjectMapper mapper;
    private GenericManager<T, PK> genericManager;

    public GenericResource(GenericManager<T, PK> genericManager) {
        this.genericManager = genericManager;
    }

    /*@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public T create(T entity) {
        logDebug("create(): " + entity);
        return this.genericManager.save(entity);
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String read(@PathParam("id") PK id) throws IOException {
        logDebug("read(id)");
        T entity = this.genericManager.get(id);
        if (entity == null) {
            throw new WebApplicationException(404);
        }
        return getExtendedViewWriter().writeValueAsString(entity);
    }

    @Path("{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public T update(@PathParam("id") PK id, T entity) {
        logDebug("update(): " + entity);
        return this.genericManager.save(entity);
    }

    @Path("{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") PK id) {
        logDebug("delete(id)");
        this.genericManager.remove(id);
    }*/

    protected ObjectMapper getMapper() {
        return mapper;
    }

    /*protected boolean isAdmin() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String anonymousUser = "anonymousUser";
        if (principal instanceof String && anonymousUser.equals(principal)) {
            return false;
        }
        UserDetails userDetails = (UserDetails) principal;

        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            if (authority.toString().equals("admin")) {
                return true;
            }
        }

        return false;
    }*/

    protected ObjectWriter getPreviewWriter() {
        return /*this.isAdmin() ? getMapper().writerWithView(JsonViews.Admin.class) :*/ getMapper().writerWithView(JsonViews.Preview.class);
    }

    /*protected ObjectWriter getExtendedViewWriter() {
        return this.isAdmin() ? getMapper().writerWithView(JsonViews.Admin.class) : getMapper().writerWithView(JsonViews.Extended.class);
    }*/

}
