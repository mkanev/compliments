package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.JsonViews;
import com.yanka.goodcauses.service.ComplimentManager;

import org.codehaus.jackson.map.ObjectMapper;
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
@Component
@Path("/compliment")
public class ComplimentResource {

    @Autowired
    private ObjectMapper mapper;
    private ComplimentManager complimentManager;

    @Autowired
    public ComplimentResource(ComplimentManager complimentManager) {
        this.complimentManager = complimentManager;
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomRecord() throws IOException {
        return mapper.writerWithView(JsonViews.Preview.class).writeValueAsString(complimentManager.getRandomEntity());
    }
}
