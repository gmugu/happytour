package com.gmugu.happytour.presenter;

/**
 * Created by mugu on 16-4-9 下午12:51.
 */
public interface IRetrievePasswordPresenter {

    /**
     * 返回
     */
    void onBackBnPressed();

    /**
     * 请求找回密码
     */
    void onRetrievePasswdBnPressed();

    /**
     * 请求取消
     */
    void onRequestCancel();
}
