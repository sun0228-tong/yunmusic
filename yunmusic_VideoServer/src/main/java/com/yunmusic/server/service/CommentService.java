package com.yunmusic.server.service;

import com.wangyi.entity.Comment;
import com.wangyi.entity.Recomment;

import java.util.List;

public interface CommentService {
    List<Recomment> findRecommentById(Integer id);
    int addComment(Comment comment);
    int addRecomment(Recomment recomment);
}
