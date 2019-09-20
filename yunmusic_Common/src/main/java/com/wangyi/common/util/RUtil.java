package com.wangyi.common.util;

import com.wangyi.common.config.Renum;
import com.wangyi.common.vo.R;

public class RUtil {

    /**
     * 设置成功*/
    public static <T> R setOK(String msg,T obj) {
        R<T> r = new R<>();
        r.setCode(Renum.SUCCESS.getCode());
        r.setMsg(msg);
        r.setData(obj);
        return r;
    }

    public static <T> R setOK(String msg){
        R<T> r=new R<>();
        r.setCode(Renum.SUCCESS.getCode());
        r.setMsg(msg);
        r.setData(null);
        return r;
    }

    public static <T> R setOK(){
        R<T> r=new R<>();
        r.setCode(Renum.SUCCESS.getCode());
        r.setMsg("OK");
        r.setData(null);
        return r;
    }

    /**
     * 设置失败*/
    public static <T> R setERROR(String msg,T obj) {
        R<T> r = new R<>();
        r.setCode(Renum.ERROR.getCode());
        r.setMsg(msg);
        r.setData(obj);
        return r;
    }

    public static <T> R setERROR(String msg){
        R<T> r=new R<>();
        r.setCode(Renum.ERROR.getCode());
        r.setMsg(msg);
        r.setData(null);
        return r;
    }

    public static <T> R setERROR(){
        R<T> r=new R<>();
        r.setCode(Renum.ERROR.getCode());
        r.setMsg("ERROR");
        r.setData(null);
        return r;
    }

    public static <T> R setR(boolean isSuccess,String msg) {
        return isSuccess?setOK(msg):setERROR(msg);
    }
}
