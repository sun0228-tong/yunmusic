package com.yunmusic.server.controller;

import com.wangyi.common.util.RUtil;
import com.wangyi.common.vo.R;
import com.wangyi.entity.Comment;
import com.wangyi.entity.Recomment;
import com.yunmusic.server.service.CommentService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/server/comment/findRecommentById.do")
    public R findRecommentById(@RequestParam("id") Integer id){
        return RUtil.setOK("根据评论id查找回复评论",commentService.findRecommentById(id));
    }
    @PostMapping("/server/comment/addComment.do")
    public R addComment (@RequestBody Comment comment){
        try {
            return RUtil.setOK("添加评论",commentService.addComment(comment));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/server/comment/addRecomment.do")
    public R addRecomment (@RequestBody Recomment recomment){
        try {
            return RUtil.setOK("回复评论",commentService.addRecomment(recomment));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
