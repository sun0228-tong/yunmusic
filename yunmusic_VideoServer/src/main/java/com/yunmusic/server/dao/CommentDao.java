package com.yunmusic.server.dao;

import com.wangyi.entity.Comment;
import com.wangyi.entity.Recomment;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao {
    /**
     * 通过id查看评论的所有回复
     */

    List<Recomment> findRecommentById(@Param("id")Integer id);
    /**
     * 添加视频评论
     */

    int addComment(Comment comment);

    /**
     * 回复评论
     */
    int addRecomment(Recomment recomment);

}
