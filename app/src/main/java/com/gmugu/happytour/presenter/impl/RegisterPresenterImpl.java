package com.gmugu.happytour.presenter.impl;

import com.gmugu.happytour.comment.assist.Checker;
import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happyhour.message.request.RegisterCaptchaRequest;
import com.gmugu.happyhour.message.request.RegisterRequest;
import com.gmugu.happyhour.message.result.RegisterCaptchaResult;
import com.gmugu.happyhour.message.result.RegisterResult;
import com.gmugu.happytour.presenter.IRegisterPresenter;
import com.gmugu.happytour.user.User;
import com.gmugu.happytour.view.IRegisterView;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mugu on 16-4-9 下午1:16.
 */
public class RegisterPresenterImpl implements IRegisterPresenter {

    private IRegisterView view;
    private IApiService apiService;
    private Call<RegisterCaptchaResult> registerCaptchaCall;
    private Call<RegisterResult> registerCall;

    public RegisterPresenterImpl(IRegisterView view, IApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }


    @Override
    public void onBackBnPressed() {
        view.back();
    }

    @Override
    public void onGetTokenBnPressed() {
        String eMail = view.getEMail();
        if (!Checker.isEMail(eMail)) {
            view.showToast("邮箱格式不正确!");
            return;
        }
        RegisterCaptchaRequest request = new RegisterCaptchaRequest();
        request.setCaptchaType(RegisterCaptchaRequest.CaptchaType.EMAIL);
        request.setVerifiable(eMail);
        registerCaptchaCall = apiService.getRegisterCaptcha(request);
        registerCaptchaCall.enqueue(new Callback<RegisterCaptchaResult>() {
            @Override
            public void onResponse(Call<RegisterCaptchaResult> call, Response<RegisterCaptchaResult> response) {
                view.cancelWaitingTip();
                RegisterCaptchaResult result = response.body();
                if (result == null) {
                    view.showErrorMsgDialog("连接失败");
                } else if (result.getCode() != 0) {
                    view.showErrorMsgDialog(result.getMessage());
                } else {
                    view.showMsgDialog("请登录邮箱查看验证码");
                }
            }

            @Override
            public void onFailure(Call<RegisterCaptchaResult> call, Throwable t) {
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
        view.showWaitingTip();
    }

    @Override
    public void onNextBnPressed() {
        try {
            view.toModifyUserInfo();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        String eMail = view.getEMail();
        String token = view.getToken();
        String passwd = view.getPasswd();
        String passwdRepate = view.getPasswdRepate();
        if (!Checker.isEMail(eMail)) {
            view.showToast("邮箱格式不正确!");
            return;
        }
        if (Checker.isEmpty(token)) {
            view.showToast("验证码不能为空");
            return;
        }
        if (!Checker.isEffectivePasswd(passwd)) {
            view.showToast("密码格式不正确");
            return;
        }
        if (!Checker.isEquals(passwd, passwdRepate)) {
            view.showToast("两次输入密码不一致");
            return;
        }
        final RegisterRequest request = new RegisterRequest(null, null, eMail, passwd, token);

        registerCall = apiService.register(request);
        registerCall.enqueue(new Callback<RegisterResult>() {
            @Override
            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                view.cancelWaitingTip();
                RegisterResult result = response.body();
                if (result == null) {
                    view.showErrorMsgDialog("连接失败");
                } else if (result.getCode() != 0) {
                    view.showErrorMsgDialog(result.getMessage());
                } else {
                    User.getInstance().setUserId(result.getUserId());
                    view.showToast("注册成功,请完善个人信息");
                    view.toModifyUserInfo();
                }
            }

            @Override
            public void onFailure(Call<RegisterResult> call, Throwable t) {
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
        view.showWaitingTip();
    }

    @Override
    public void onRequestCancal() {
        if (registerCaptchaCall != null && !registerCaptchaCall.isCanceled()) {
            registerCaptchaCall.cancel();
        } else if (registerCall != null && !registerCall.isCanceled()) {
            registerCall.cancel();
        }
    }


}
