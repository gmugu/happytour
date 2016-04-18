package com.gmugu.happyhour.message.request;

/**
 * 登录
 * Created by mugu on 16-4-3 下午8:24.
 */
public class LoginRequest extends BaseRequest {

    //用户名登录(暂时不实现)
    String username;
    //手机登录(暂时不实现)
    String phoneNum;
    //邮箱登录
    String email;
    //登录密码
    String passwd;

    public LoginRequest() {
    }

    public LoginRequest(String username, String phoneNum, String email, String passwd) {
        this.username = username;
        this.phoneNum = phoneNum;
        this.email = email;
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
