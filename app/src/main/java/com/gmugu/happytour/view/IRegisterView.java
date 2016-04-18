package com.gmugu.happytour.view;

/**
 * Created by mugu on 16-4-9 下午12:59.
 */
public interface IRegisterView {

    void back();

    String getEMail();

    String getToken();

    String getPasswd();

    String getPasswdRepate();

    void showToast(String msg);

    void showErrorMsgDialog(String msg);

    void showMsgDialog(String msg);

    void toModifyUserInfo();

    void cancelWaitingTip();

    void showWaitingTip();
}
