package com.gmugu.happyhour.message.result;

import com.gmugu.happyhour.message.UserInfoModel;

/**
 * 注册操作返回的结果
 * Created by mugu on 16-4-3 下午5:47.
 */
public class RegisterResult extends BaseResult {

    //用户ID
    private Integer userId;

    private UserInfoModel userInfoModel;

    public RegisterResult() {
    }

    public RegisterResult(int code, String message, Integer userId, UserInfoModel userInfoModel) {
        super(code, message);
        this.userId = userId;
        this.userInfoModel = userInfoModel;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }
}
