package com.wangyi.msg.service.impl;

import com.wangyi.common.config.RedisKeyConfig;
import com.wangyi.common.util.*;
import com.wangyi.common.vo.R;
import com.wangyi.msg.entity.SmsBean;
import com.wangyi.msg.service.SmsService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    // 发送短信限制条件： 1条/分钟，5条/小时，10条/天
    // 验证码5分钟  Redis
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public R sendSms(String phone) {

        // 1. 验证今天发送信息数量有没有超标
        if (RedissonUtil.checkKey(RedisKeyConfig.SMS_DAY + phone)) {
            int dayCount = Integer.parseInt(RedissonUtil.getStr(RedisKeyConfig.SMS_DAY + phone));
            if (dayCount > 10) {
                return RUtil.setERROR("今日发送信息数量已达上限");
            }

        }

        // 2. 验证 1小时内发送信息数量有没有超标
        if (RedissonUtil.checkKey(RedisKeyConfig.SMS_HOUR + phone)) {
            int hourCount = Integer.parseInt(RedissonUtil.getStr(RedisKeyConfig.SMS_HOUR + phone));
            if (hourCount > 5) {
                return RUtil.setERROR("本小时发送信息数量已达上限");
            }
        }

        // 3. 验证 1分钟内发送信息数量有没有超标
        if (RedissonUtil.checkKey(RedisKeyConfig.SMS_MIN + phone)) {
            return RUtil.setERROR("已经发过，请及时查看手机");
        }

        // 4. 验证验证码是否需要重新生成
        int code = 0;
        boolean isFirst = false;

        if (RedissonUtil.checkKey(RedisKeyConfig.SMS_VCODE + phone)) {
            code = Integer.parseInt(RedissonUtil.getStr(RedisKeyConfig.SMS_VCODE + phone));
        } else {
            code= RandomNumUtil.createNum(6);
            isFirst = true;
        }

        // 5. 发送短信 (使用 rabbitMQ消息队列异步执行发送短信以及记录消息步骤，减轻服务器压力，提高性能)
//        boolean hasSend = AliYunSmsUtil.sendCodeSms(phone,code);
//        if (hasSend) {
//            //存储验证 5分钟有效
//            if (isFirst) {
//                RedissonUtil.saveStr(RedisKeyConfig.SMS_VCODE + phone,code + "",300);
//            }
//
//            // 记录手机号今天发送信息次数
//            saveCount(RedisKeyConfig.SMS_DAY + phone, TimeUtil.getLastSeconds());
//            // 记录手机号本小时发送信息次数
//            saveCount(RedisKeyConfig.SMS_HOUR + phone,3600);
//            // 记录手机号当前分钟发送信息次数
//            RedissonUtil.saveStr(RedisKeyConfig.SMS_MIN + phone,"1",60);
//            return RUtil.setOK("短信验证码发送成功，注意查收");
//        } else {
//            return RUtil.setERROR("短信发送异常");
//        }
        System.out.println("发送短信开始:" + phone + ":" + code + ":" + isFirst);
        amqpTemplate.convertAndSend("SEND_MSG","发送短信开始:" + phone + ":" + code + ":" + isFirst);
        return RUtil.setOK("信息已发送");

    }

    @Override
    public R checkCode(SmsBean smsBean) {

        if (RedissonUtil.checkKey(RedisKeyConfig.SMS_VCODE + smsBean.getPhone())) {
            //验证码未失效
            int code = Integer.parseInt(RedissonUtil.getStr(RedisKeyConfig.SMS_VCODE + smsBean.getPhone()));
            if (code == smsBean.getCode()) {
                //验证码一致
                return RUtil.setOK("校验成功");
            } else {
                return RUtil.setERROR("校验失败");
            }
        } else {
            return RUtil.setERROR("验证码是失效的");
        }
    }

    private void saveCount(String key,int time) {
        if (RedissonUtil.checkKey(key)) {
            RedissonUtil.saveStr(key,Integer.parseInt(RedissonUtil.getStr(key)) + 1 + "",RedissonUtil.getTTL(key));
        } else {
            RedissonUtil.saveStr(key,1 + "",time);
        }
    }
}
