package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.Comment;
import com.yanka.goodcauses.repository.CommentDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Service
public class CommentManagerImpl extends GenericEntityManagerImpl<Comment> implements CommentManager {

    private CommentDAO commentDAO;

    @Autowired
    public CommentManagerImpl(CommentDAO commentDAO) {
        super(commentDAO);
        this.commentDAO = commentDAO;
    }
}
