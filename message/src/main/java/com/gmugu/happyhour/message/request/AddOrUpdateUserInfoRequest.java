package com.gmugu.happyhour.message.request;


import com.gmugu.happyhour.message.UserInfoModel;

/**
 * 修改用户信息
 * Created by mugu on 16-4-4 下午2:20.
 */
public class AddOrUpdateUserInfoRequest extends BaseRequest {
    //用户信息
    private UserInfoModel userInfoModel;

    public AddOrUpdateUserInfoRequest() {
    }

    public AddOrUpdateUserInfoRequest(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }
}
