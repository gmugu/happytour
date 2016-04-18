package com.gmugu.happytour.view;

/**
 * Created by mugu on 16-4-9 下午12:59.
 */
public interface IRetrievePasswordView {

    void back();

    String getEMail();

    void showToast(String msg);

    void showErrorMsgDialog(String msg);

    void showwaitingTip();

    void cancelWaitingTip();

    void showMsgDialog(String msg);
}
