package com.gmugu.happyhour.message.request;

/**
 * Created by mugu on 16-4-4 下午5:47.
 */
public class RetrievePasswordRequest extends BaseRequest {

    private String email;

    public RetrievePasswordRequest() {
    }

    public RetrievePasswordRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
