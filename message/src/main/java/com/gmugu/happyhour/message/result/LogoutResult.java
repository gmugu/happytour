package com.gmugu.happyhour.message.result;

/**
 * 注销操作返回的结果
 * Created by mugu on 16-4-3 下午5:47.
 */
public class LogoutResult extends BaseResult {

    public LogoutResult() {
    }

    public LogoutResult(int code, String message) {
        super(code, message);
    }
}
