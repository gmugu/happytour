package com.gmugu.happytour.user;


import com.gmugu.happyhour.message.UserInfoModel;

/**
 * Created by mugu on 16-3-1 下午1:18.
 */
public class User {
    private static User instance;
    private String userId = "anonymous";
    private UserInfoModel userInfoModel;
    private String userName;
    private String passwd;

    private User() {
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }
}
