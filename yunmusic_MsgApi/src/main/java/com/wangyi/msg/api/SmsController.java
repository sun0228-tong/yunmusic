package com.wangyi.msg.api;

import com.wangyi.common.vo.R;
import com.wangyi.msg.entity.SmsBean;
import com.wangyi.msg.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "信息服务模块",tags = "信息服务模块")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("api/sms/checkcode.do")
    @ApiOperation(value = "校验信息验证码",notes = "校验信息验证码")
    public R check(@RequestBody SmsBean smsBean) {
        return smsService.checkCode(smsBean);
    }

    @PostMapping("api/sms/sendcode.do")
    @ApiOperation(value = "发送信息",notes = "发送信息")
    public R sendSms(String phone){
        return smsService.sendSms(phone);
    }
}
