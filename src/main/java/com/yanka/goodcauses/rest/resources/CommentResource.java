package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.model.Comment;
import com.yanka.goodcauses.service.CommentManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.ws.rs.Path;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Component
@Path("/comment")
public class CommentResource extends GenericEntityResource<Comment> {

    private CommentManager commentManager;

    @Autowired
    public CommentResource(CommentManager commentManager) {
        super(commentManager);
        this.commentManager = commentManager;
    }

    @Override
    protected List<Comment> getEntityList() {
        return commentManager.getExistingEntityList();
    }
}
