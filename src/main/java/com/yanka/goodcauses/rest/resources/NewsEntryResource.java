package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.JsonViews;
import com.yanka.goodcauses.model.NewsEntry;
import com.yanka.goodcauses.repository.NewsEntryDAO;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;


@Component
@Path("/news")
public class NewsEntryResource {

    private final Logger logger = Logger.getLogger(NewsEntryResource.class.getName());

    @Autowired
    private NewsEntryDAO newsEntryDao;

    @Autowired
    private ObjectMapper mapper;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String list() throws IOException {

        this.logger.info("list()");

        ObjectWriter viewWriter;
        if (this.isAdmin()) {
            viewWriter = this.mapper.writerWithView(JsonViews.Admin.class);
        } else {
            viewWriter = this.mapper.writerWithView(JsonViews.User.class);
        }
        List<NewsEntry> allEntries = this.newsEntryDao.getAll();

        return viewWriter.writeValueAsString(allEntries);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public NewsEntry read(@PathParam("id") Long id) {

        this.logger.info("read(id)");

        NewsEntry newsEntry = this.newsEntryDao.getEntity(id);
        if (newsEntry == null) {
            throw new WebApplicationException(404);
        }
        return newsEntry;
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public NewsEntry create(NewsEntry newsEntry) {

        this.logger.info("create(): " + newsEntry);

        return this.newsEntryDao.saveEntity(newsEntry);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public NewsEntry update(@PathParam("id") Long id, NewsEntry newsEntry) {

        this.logger.info("update(): " + newsEntry);

        return this.newsEntryDao.saveEntity(newsEntry);
    }


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {

        this.logger.info("delete(id)");

        this.newsEntryDao.deleteEntity(id);
    }


    private boolean isAdmin() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
            return false;
        }
        UserDetails userDetails = (UserDetails) principal;

        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            if (authority.toString().equals("admin")) {
                return true;
            }
        }

        return false;
    }

}