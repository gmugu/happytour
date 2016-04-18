package com.gmugu.happytour.view.activity.module;


import android.app.Fragment;
import android.content.Context;

import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happytour.data.spf.ISpfManager;
import com.gmugu.happytour.presenter.IModifyUserInfoPresenter;
import com.gmugu.happytour.presenter.IRegisterPresenter;
import com.gmugu.happytour.presenter.impl.ModifyUserInfoPresenterImpl;
import com.gmugu.happytour.presenter.impl.RegisterPresenterImpl;
import com.gmugu.happytour.view.IModifyUserInfoView;
import com.gmugu.happytour.view.IRegisterView;
import com.gmugu.happytour.view.activity.RegisterActivity;
import com.gmugu.happytour.view.activity.Scope.ActivityScope;
import com.gmugu.happytour.view.fragment.ModifyUserInfoFragment;
import com.gmugu.happytour.view.fragment.RegisterFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mugu on 16-4-9 下午2:41.
 */
@Module
public class RegisterModule {

    private RegisterActivity registerActivity;

    public RegisterModule(RegisterActivity registerActivity) {
        this.registerActivity = registerActivity;
    }

    @ActivityScope
    @Provides
    @Named("RegisterFragment")
    public Fragment provideRegisterFragment() {
        return RegisterFragment.newInstance();
    }

    @Provides
    public IRegisterPresenter provideRegisterPresenter(IRegisterView view, IApiService apiService) {
        return new RegisterPresenterImpl(view, apiService);
    }

    @Provides
    public IRegisterView provideRegisterView(@Named("RegisterFragment") Fragment fragment) {
        return (IRegisterView) fragment;
    }

    /******************************************************/

    @ActivityScope
    @Provides
    @Named("ModifyUserInfoFragment")
    public Fragment provideModifyUserInfoFragment() {
        ModifyUserInfoFragment modifyUserInfoFragment = ModifyUserInfoFragment.newInstance(null);
        return modifyUserInfoFragment;
    }

    @Provides
    public IModifyUserInfoPresenter provodeModifyUserInfoPresenter(IModifyUserInfoView view, IApiService apiService, ISpfManager spfManager, Context context) {
        return new ModifyUserInfoPresenterImpl(view, apiService, spfManager, context);
    }

    @Provides
    public IModifyUserInfoView provideModifyUserInfoView(@Named("ModifyUserInfoFragment") Fragment fragment) {
        return (IModifyUserInfoView) fragment;
    }
}
