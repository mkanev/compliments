package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.model.NewsEntry;
import com.yanka.goodcauses.model.User;
import com.yanka.goodcauses.service.NewsEntryManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    protected List<NewsEntry> getEntityList() {
        return newsEntryManager.getExistingEntityList();
    }

    @Override
    public NewsEntry create(NewsEntry entity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            User currentUser = (User)authentication.getPrincipal();
            entity.setAuthor(currentUser);
        }
        return super.create(entity);
    }
}