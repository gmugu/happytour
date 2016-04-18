package com.gmugu.happyhour.message.request;


/**
 * 注册
 * Created by mugu on 16-4-3 下午5:59.
 */
public class RegisterRequest extends BaseRequest {
    //用户名登录(暂不实现)
    private String username;
    //手机登录(暂不实现)
    private String phoneNum;
    //邮箱登录
    private String email;
    //密码
    private String passwd;
    //邮箱注册时需填从邮箱收到的验证码
    private String token;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String phoneNum, String email, String passwd, String token) {
        this.username = username;
        this.phoneNum = phoneNum;
        this.email = email;
        this.passwd = passwd;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
