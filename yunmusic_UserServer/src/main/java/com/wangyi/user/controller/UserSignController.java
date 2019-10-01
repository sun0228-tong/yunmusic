package com.wangyi.user.controller;

import com.wangyi.common.vo.R;
import com.wangyi.user.service.UserSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserSignController {

    @Autowired(required = false)
    private UserSignService userSignService;

    //检查是否可以签到
    @GetMapping("server/usersign/checksign.do")
    public R checkSign(HttpServletRequest request) {
        return userSignService.checkSign(request.getHeader("usertoken"));
    }

    //用户签到
    @GetMapping("server/usersign/savesign.do")
    public R saveSign(HttpServletRequest request) {
        String usertoken = request.getHeader("usertoken");
        System.out.println(usertoken);
        return userSignService.save(request.getHeader("usertoken"));
    }

    //查询用户当前月的签到数据
    @GetMapping("server/usersign/signmonth.do")
    public R signMonth(HttpServletRequest request) {
        return userSignService.queryMonth(request.getHeader("usertoken"));
    }

    //检查是否可以抽奖
    @GetMapping("server/usersign/checkLotto.do")
    public R checkLotto(HttpServletRequest request) {
        return userSignService.checkLotto(request.getHeader("usertoken"));
    }

    //开始抽奖 消耗 10壹壳
    @GetMapping("server/usersign/startLotto.do")
    public R startLotto(HttpServletRequest request) {
        return userSignService.startLotto(request.getHeader("usertoken"));
    }
}
