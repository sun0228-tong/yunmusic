package com.wangyi.msg.service;

import com.wangyi.common.vo.R;
import com.wangyi.msg.entity.SmsBean;

public interface SmsService {

    R sendSms(String phone);

    R checkCode(SmsBean smsBean);
}
