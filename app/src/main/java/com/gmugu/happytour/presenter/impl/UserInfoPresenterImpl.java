package com.gmugu.happytour.presenter.impl;

import android.content.Context;

import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happytour.data.spf.ISpfManager;
import com.gmugu.happytour.presenter.IUserInfoPresenter;
import com.gmugu.happytour.view.IUserInfoView;

/**
 * Created by mugu on 16-4-17 下午2:29.
 */
public class UserInfoPresenterImpl implements IUserInfoPresenter {
    private IUserInfoView view;
    private IApiService apiService;
    private ISpfManager spfManager;
    private Context context;

    public UserInfoPresenterImpl(IUserInfoView view, IApiService apiService, ISpfManager spfManager, Context context) {

        this.view = view;
        this.apiService = apiService;
        this.spfManager = spfManager;
        this.context = context;
    }
}
