package com.gmugu.happyhour.message.request;

/**
 * 注册时获取验证码
 * Created by mugu on 16-4-4 下午2:09.
 */
public class RegisterCaptchaRequest extends BaseRequest {


    public enum CaptchaType {
        USERNAME,
        PHONE_NUMBER,
        EMAIL
    }

    private CaptchaType captchaType;
    private String verifiable;

    public RegisterCaptchaRequest() {
    }

    public RegisterCaptchaRequest(CaptchaType captchaType, String verifiable) {
        this.captchaType = captchaType;
        this.verifiable = verifiable;
    }

    public CaptchaType getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(CaptchaType captchaType) {
        this.captchaType = captchaType;
    }

    public String getVerifiable() {
        return verifiable;
    }

    public void setVerifiable(String verifiable) {
        this.verifiable = verifiable;
    }
}
