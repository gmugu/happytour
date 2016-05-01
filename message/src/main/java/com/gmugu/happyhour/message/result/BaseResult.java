package com.gmugu.happyhour.message.result;

import java.io.Serializable;


/**
 * 服务器返回的基类结果
 * Created by mugu on 16-4-3 下午5:37.
 */
public class BaseResult implements Serializable {


    //执行结果,0表示成功，默认为成功
    protected int code = 0;

    //附带的消息
    protected String message = null;

    public BaseResult() {
    }

    public BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
