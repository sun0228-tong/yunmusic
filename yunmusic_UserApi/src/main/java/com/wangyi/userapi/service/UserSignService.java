package com.wangyi.userapi.service;

import com.wangyi.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@FeignClient("UserProvider")
public interface UserSignService {

    //检查是否可以签到
    @GetMapping("server/usersign/checksign.do")
    R checkSign();

    //用户签到
    @GetMapping("server/usersign/savesign.do")
    R saveSign();

    //查询用户当前月的签到数据
    @GetMapping("server/usersign/signmonth.do")
    R signMonth();

    //检查是否可以抽奖
    @GetMapping("server/usersign/checkLotto.do")
    R checkLotto();

    //开始抽奖 消耗 10壹壳
    @GetMapping("server/usersign/startLotto.do")
    R startLotto();
}
