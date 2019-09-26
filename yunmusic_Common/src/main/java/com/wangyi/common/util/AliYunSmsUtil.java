package com.wangyi.common.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;

/**
 * @program: yunmusic
 * @description:
 * @author: suntong
 * @create: 2019-09-26 10:13
 */
public class AliYunSmsUtil {
    //发送短信验证码
    public static boolean sendCodeSms(String phone,int code){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "LTAIhTvqTSmlmjeQ", "X7X9w0Ck5GEIWgP9tl0Q6sgmFjQuMv");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "来自邢朋辉的短信");
        request.putQueryParameter("TemplateCode", "SMS_114390520");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            String json=response.getData();
            SmsResult sr= JSON.parseObject(json,SmsResult.class);
            return sr.getCode().equals("OK");
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Data
   public static class SmsResult {
        private String Message;
        private String Code;
    }
}