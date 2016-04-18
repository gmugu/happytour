package com.gmugu.happyhour.message.result;


import com.gmugu.happyhour.message.UserInfoModel;

/**
 * 登录操作返回的结果
 * Created by mugu on 16-4-3 下午5:43.
 */
public class LoginResult extends BaseResult {

    //用户信息
    private UserInfoModel userInfoModel;

    public LoginResult() {
    }

    public LoginResult(int code, String message, UserInfoModel userInfoModel) {
        super(code, message);
        this.userInfoModel = userInfoModel;
    }

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }
}
