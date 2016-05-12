package com.gmugu.happytour.view;

/**
 * Created by mugu on 16-4-22 上午10:08.
 */
public interface IMessageView {

    void showErrorMsgDialog(String msg);

    void showToase(String msg);

    void showWaitingTip();

    void cancelWaitingTip();

    void onWaitingTipCancel();
}
