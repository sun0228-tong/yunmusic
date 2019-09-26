package com.wangyi.msg.rabbitMQ;

import com.wangyi.common.config.RedisKeyConfig;
import com.wangyi.common.util.AliYunSmsUtil;
import com.wangyi.common.util.RUtil;
import com.wangyi.common.util.RedissonUtil;
import com.wangyi.common.util.TimeUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ExecSendMsg {

    @RabbitListener(queues = "SEND_MSG")
    public void receiveMsg(String msg) {
        System.out.println("收到消息");

        // 获取信息 格式为: "发送短信开始:phone:code:isFirst"
        String[] split = msg.split(":");
        String phone = split[1];
        Integer code = Integer.parseInt(split[2]);
        boolean isFirst;
        if (split[3].equals("true")) {
            isFirst = true;
        } else {
            isFirst = false;
        }
        System.out.println(isFirst);

        // 发送短信 (使用 rabbitMQ消息队列异步执行发送短信以及记录消息步骤，减轻服务器压力，提高性能)
        boolean hasSend = AliYunSmsUtil.sendCodeSms(phone,code);
        System.out.println(hasSend);
        if (hasSend) {
            //存储验证 5分钟有效
            if (isFirst) {
                RedissonUtil.saveStr(RedisKeyConfig.SMS_VCODE + phone,code + "",300);
            }

            // 记录手机号今天发送信息次数
            saveCount(RedisKeyConfig.SMS_DAY + phone, TimeUtil.getLastSeconds());
            // 记录手机号本小时发送信息次数
            saveCount(RedisKeyConfig.SMS_HOUR + phone,3600);
            // 记录手机号当前分钟发送信息次数
            RedissonUtil.saveStr(RedisKeyConfig.SMS_MIN + phone,"1",60);
            //return RUtil.setOK("短信验证码发送成功，注意查收");
        } else {
            //return RUtil.setERROR("短信发送异常");
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
