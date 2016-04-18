package com.gmugu.happytour.view;

/**
 * Created by mugu on 16-4-9 下午12:59.
 */
public interface ILoginView {
    String getUserName();

    String getPasswd();

    void showErrorMsgDialog(String message);

    void close();

    void toMainView();

    void toRegister();

    void toRetrievePassword();

    void showLoginingTip();

    void cancelLoginingTip();
}
