package com.gmugu.happyhour.message.request;

/**
 * Created by mugu on 16-4-4 下午5:52.
 */
public class UpdatePasswdRequest extends BaseRequest {

    private String oldPasswd;
    private String newPasswd;

    public UpdatePasswdRequest() {
    }

    public UpdatePasswdRequest(String oldPasswd, String newPasswd) {
        this.oldPasswd = oldPasswd;
        this.newPasswd = newPasswd;
    }

    public String getOldPasswd() {
        return oldPasswd;
    }

    public void setOldPasswd(String oldPasswd) {
        this.oldPasswd = oldPasswd;
    }

    public String getNewPasswd() {
        return newPasswd;
    }

    public void setNewPasswd(String newPasswd) {
        this.newPasswd = newPasswd;
    }
}
