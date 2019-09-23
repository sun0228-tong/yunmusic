package com.yunmusic.server.service.impl;

import com.wangyi.entity.Comment;
import com.wangyi.entity.Recomment;
import com.yunmusic.server.dao.CommentDao;
import com.yunmusic.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl  implements CommentService {
    @Autowired(required = false)
    private CommentDao commentDao;


    @Override
    public List<Recomment> findRecommentById(Integer id) {
        return commentDao.findRecommentById(id);
    }

    @Override
    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    public int addRecomment(Recomment recomment) {
        return commentDao.addRecomment(recomment);
    }
}
