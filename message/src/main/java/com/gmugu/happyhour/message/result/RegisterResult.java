package com.gmugu.happyhour.message.result;

/**
 * 注册操作返回的结果
 * Created by mugu on 16-4-3 下午5:47.
 */
public class RegisterResult extends BaseResult {

    //用户ID
    private Integer userId;

    public RegisterResult() {
    }

    public RegisterResult(int code, String message, Integer userId) {
        super(code, message);
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
