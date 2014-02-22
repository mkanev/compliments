package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.model.NewsEntry;
import com.yanka.goodcauses.service.NewsEntryManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;


@Component
@Path("/news")
public class NewsEntryResource extends ContainingMediaEntityResource<NewsEntry> {

    private NewsEntryManager newsEntryManager;

    @Autowired
    public NewsEntryResource(NewsEntryManager newsEntryManager) {
        super(newsEntryManager);
        this.newsEntryManager = newsEntryManager;
    }

}