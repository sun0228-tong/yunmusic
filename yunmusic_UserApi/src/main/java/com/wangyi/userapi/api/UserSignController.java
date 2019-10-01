package com.wangyi.userapi.api;

import com.wangyi.common.vo.R;
import com.wangyi.userapi.service.UserSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value = "用户签到及抽奖",tags = "用户签到及抽奖")
public class UserSignController {

    @Autowired
    private UserSignService userSignService;

    @ApiOperation(value = "校验今日能否签到",notes = "校验今日能否签到")
    @GetMapping("api/usersign/checksign.do")
     public R checkSign(HttpServletRequest request) {
        return userSignService.checkSign();
    }

    @ApiOperation(value = "今日签到",notes = "今日签到")
    @GetMapping("api/usersign/savesign.do")
    public R saveSign(HttpServletRequest request) {
        return userSignService.saveSign();
    }

    @ApiOperation(value = "本月的签到数据",notes = "本月的签到数据")
    @GetMapping("api/usersign/signmonth.do")
    public R signMonth(HttpServletRequest request) {
        return userSignService.signMonth();
    }

    @ApiOperation(value = "检查今日是否能抽奖",notes = "检查今日是否能抽奖")
    @GetMapping("api/usersign/checkLotto.do")
    public R checkLotto(HttpServletRequest request) {
        return userSignService.checkLotto();
    }

    @ApiOperation(value = "开始抽奖",notes = "开始抽奖")
    @GetMapping("api/usersign/startLotto.do")
    public R startLotto(HttpServletRequest request) {
        return userSignService.startLotto();
    }
}
