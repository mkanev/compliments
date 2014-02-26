package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.Comment;

import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Repository
public class CommentDAOImpl extends GenericEntityDAOImpl<Comment> implements CommentDAO {

    public CommentDAOImpl() {
        super(Comment.class);
    }
}
