package com.gmugu.happytour.presenter.impl;

import com.gmugu.happytour.comment.assist.Checker;
import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happyhour.message.request.RetrievePasswordRequest;
import com.gmugu.happyhour.message.result.RetrievePasswordResult;
import com.gmugu.happytour.presenter.IRetrievePasswordPresenter;
import com.gmugu.happytour.view.IRetrievePasswordView;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mugu on 16-4-11 下午8:40.
 */
public class RetrievePasswordPresenterImpl implements IRetrievePasswordPresenter {

    private IRetrievePasswordView view;
    private IApiService apiService;
    private Call<RetrievePasswordResult> retrievePasswordResultCall;

    public RetrievePasswordPresenterImpl(IRetrievePasswordView view, IApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    public void onBackBnPressed() {
        view.back();
    }

    @Override
    public void onRetrievePasswdBnPressed() {
        String eMail = view.getEMail();
        if (!Checker.isEMail(eMail)) {
            view.showToast("邮箱格式不正确!");
            return;
        }
        RetrievePasswordRequest request = new RetrievePasswordRequest();
        request.setEmail(eMail);
        retrievePasswordResultCall = apiService.retrievePassword(request);
        retrievePasswordResultCall.enqueue(new Callback<RetrievePasswordResult>() {
            @Override
            public void onResponse(Call<RetrievePasswordResult> call, Response<RetrievePasswordResult> response) {
                view.cancelWaitingTip();
                RetrievePasswordResult result = response.body();
                if (result == null) {
                    view.showErrorMsgDialog("连接失败");
                } else if (result.getCode() != 0) {
                    view.showErrorMsgDialog(result.getMessage());
                } else {
                    view.showToast("请登录邮箱修改密码");
                    view.back();
                }
            }

            @Override
            public void onFailure(Call<RetrievePasswordResult> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof SocketTimeoutException) {
                    view.showErrorMsgDialog("连接超时");
                } else if (t instanceof SocketException && t.getMessage().equals("Socket closed")) {

                } else {
                    view.showErrorMsgDialog("请求失败");
                }
                view.cancelWaitingTip();
            }
        });
        view.showwaitingTip();
    }

    @Override
    public void onRequestCancel() {
        retrievePasswordResultCall.cancel();
    }


}
