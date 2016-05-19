package com.gmugu.happytour.presenter.impl;

import com.gmugu.happytour.comment.assist.Checker;
import com.gmugu.happytour.comment.assist.Log;
import com.gmugu.happytour.comment.utils.MD5Util;
import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happytour.data.spf.ISpfManager;
import com.gmugu.happytour.data.spf.SpfManagetException;
import com.gmugu.happyhour.message.UserInfoModel;
import com.gmugu.happyhour.message.request.LoginRequest;
import com.gmugu.happyhour.message.result.LoginResult;
import com.gmugu.happytour.presenter.ILoginPresenter;
import com.gmugu.happytour.user.User;
import com.gmugu.happytour.view.ILoginView;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mugu on 16-4-9 下午1:14.
 */
public class LoginPresenterImpl implements ILoginPresenter {


    private ILoginView loginView;
    private IApiService apiService;
    private ISpfManager spfManager;
    private Call<LoginResult> loginCall;

    public LoginPresenterImpl(ILoginView loginView, IApiService apiService, ISpfManager spfManager) {
        this.loginView = loginView;
        this.apiService = apiService;
        this.spfManager = spfManager;
    }

    @Override
    public void onLoginBnPressed() {
//        Call<ResponseBody> responseBodyCall = apiService.downloadFile("js/base.do");
//        responseBodyCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                try {
//                    Log.e(this, response.body().string());
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });


        String userName = loginView.getUserName();
        String passwd = loginView.getPasswd();

        //root后门登录:)
        if (userName.equals("root") && passwd.equals("admin")) {
            UserInfoModel userInfoModel = new UserInfoModel();
            userInfoModel.setNickname("BIG BOSS!");
            User.getInstance().setUserInfoModel(userInfoModel);
            loginView.toMainView();
            loginView.close();
            return;
        }

        if (!Checker.isEffectiveUsername(userName)) {
            loginView.showErrorMsgDialog("请输入有效用户名");
            return;
        }
        if (!Checker.isEffectivePasswd(passwd)) {
            loginView.showErrorMsgDialog("请输入有效密码");
            return;
        }

        loginCall = apiService.login(new LoginRequest(null, null, userName, passwd));

        loginCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                loginView.cancelLoginingTip();
                LoginResult result = response.body();
                if (result == null) {
                    loginView.showErrorMsgDialog("连接失败");
                } else if (result.getCode() != 0) {
                    loginView.showErrorMsgDialog(result.getMessage());
                } else {
                    UserInfoModel userInfoModel = result.getUserInfoModel();
                    Integer userId = userInfoModel.getUserId();
                    try {
                        UserInfoModel userInfo = spfManager.getUserInfo(userId + "");
                        String portraitCheckCode = userInfo.getPortraitCheckCode();
                        if (!portraitCheckCode.equals(userInfoModel.getPortraitCheckCode())) {
                            throw new Exception("should update head image");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        //下载头像文件
                        downloadHeadimg(userInfoModel);
                    }
                    userInfoModel.setPortrait(spfManager.getHeadimgPath(userId+""));
                    User user = User.getInstance();
                    user.setUserId(userId);
                    user.setUserInfoModel(userInfoModel);
                    try {
                        spfManager.saveOrUpdateUserInfo(userInfoModel.getUserId()+"", userInfoModel);
                    } catch (SpfManagetException e) {
                        e.printStackTrace();
                    }
                    loginView.toMainView();
                    loginView.close();
                }

            }

            private void downloadHeadimg(final UserInfoModel userInfoModel) {
                Call<ResponseBody> responseBodyCall = apiService.downloadFile(userInfoModel.getPortrait());
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {
                            byte[] bytes = response.body().bytes();
                            String path = spfManager.saveOrUpdateHeadimg(userInfoModel.getUserId()+"", bytes);
                            userInfoModel.setPortrait(path);
                            spfManager.saveOrUpdateUserInfo(userInfoModel.getUserId()+"", userInfoModel);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                t.printStackTrace();
                loginView.cancelLoginingTip();
                if (t instanceof SocketTimeoutException) {
                    loginView.showErrorMsgDialog("连接超时");
                } else if (t instanceof SocketException && t.getMessage().equals("Socket closed")) {

                } else {
                    loginView.showErrorMsgDialog("登录失败");
                }
            }
        });
        loginView.showLoginingTip();

    }

    @Override
    public void onRetrievePasswordBnPressed() {
        loginView.toRetrievePassword();
    }

    @Override
    public void onRegisterBnPressed() {
        loginView.toRegister();
    }

    @Override
    public void onLoginCancel() {
        loginCall.cancel();
    }

}
