package com.gmugu.happytour.view;

/**
 * Created by mugu on 16-4-9 下午12:59.
 */
public interface ILoginView {
    String getUserName();

    String getPasswd();

    void close();

    void toMainView();

    void toRegister();

    void toRetrievePassword();

    void showToase(String msg);

    void showErrorMsgDialog(String message);

    void showLoginingTip();

    void cancelLoginingTip();
}
