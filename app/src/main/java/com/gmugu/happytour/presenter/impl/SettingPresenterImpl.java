package com.gmugu.happytour.presenter.impl;

import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happyhour.message.result.CheckUpdateResult;
import com.gmugu.happytour.presenter.ISettingPresenter;
import com.gmugu.happytour.view.ISettingView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mugu on 16-4-16 下午10:27.
 */
public class SettingPresenterImpl implements ISettingPresenter {

    private IApiService apiService;
    private ISettingView view;
    private Call<CheckUpdateResult> checkUpdateResultCall;

    public SettingPresenterImpl(IApiService apiService, ISettingView settingView) {
        this.apiService = apiService;
        this.view = settingView;
    }

    @Override
    public void onModifyUserinfo() {
        view.toModifyUserinfo();
    }

    @Override
    public void onConnectDevice() {
        view.showErrorDialog();
    }

    @Override
    public void onCheckUpdate() {
        checkUpdateResultCall = apiService.checkUpdate();
        checkUpdateResultCall.enqueue(new Callback<CheckUpdateResult>() {
            @Override
            public void onResponse(Call<CheckUpdateResult> call, Response<CheckUpdateResult> response) {
                CheckUpdateResult result = response.body();
            }

            @Override
            public void onFailure(Call<CheckUpdateResult> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCleanCache() {

    }

    @Override
    public void onAbout() {

    }

    @Override
    public void onLogout() {

    }
}
