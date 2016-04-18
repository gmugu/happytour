package com.gmugu.happyhour.message.result;

/**
 * 注册操作返回的结果
 * Created by mugu on 16-4-3 下午5:47.
 */
public class RegisterResult extends BaseResult {

    //用户ID
    private String userId;

    public RegisterResult() {
    }

    public RegisterResult(int code, String message, String userId) {
        super(code, message);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
