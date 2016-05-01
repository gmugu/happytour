package com.gmugu.happyhour.message.result;

/**
 * Created by mugu on 16-4-4 下午2:04.
 */
public class GetRegisterCaptchaResult extends BaseResult {

    public GetRegisterCaptchaResult() {
    }

    public GetRegisterCaptchaResult(int code, String message) {
        super(code, message);
    }
}
