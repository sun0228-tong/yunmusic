package com.yunmusic.api.service;

import com.wangyi.common.vo.R;
import com.wangyi.entity.Comment;
import com.wangyi.entity.Recomment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("VideoProvider")
public interface VideoService {
    @PostMapping("/server/video/findVideo.do")
    R detail(@RequestParam(value = "tag") String tag);

    @PostMapping ("/server/video/findAllTag.do")
    R findAllTag();

    @PostMapping ("/server/video/findAllType.do")
    R findAllType();

    @PostMapping ("/server/video/findByType.do")
    R findByType(@RequestParam(value = "type") String type);

    @PostMapping("/server/video/selectMVByTag.do")
    R selectMVByTag(@RequestParam(value = "tag") String tag);

    @PostMapping("/server/video/selectByPlaynum.do")
    R selectByPlaynum();

    @PostMapping("/server/video/selectMVByPlaynumAndTag.do")
    R selectMVByPlaynumAndTag(@RequestParam(value = "tag") String tag);

    @PostMapping("/server/video/selectOneVideo.do")
    R selectOneVideo(@RequestParam(value = "id") int id);

    @PostMapping("/server/comment/findRecommentById.do")
    R findRecommentById(@RequestParam(value = "id") Integer id);
    @PostMapping("/server/comment/addComment.do")
    R addComment(@RequestBody Comment comment);

    @PostMapping("/server/comment/addRecomment.do")
    R addRecomment(@RequestBody Recomment recomment);
}
