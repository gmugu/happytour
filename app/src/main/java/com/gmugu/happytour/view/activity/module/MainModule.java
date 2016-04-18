package com.gmugu.happytour.view.activity.module;

import android.app.Fragment;
import android.content.Context;

import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happytour.data.spf.ISpfManager;
import com.gmugu.happytour.presenter.IMainPresenter;
import com.gmugu.happytour.presenter.IModifyUserInfoPresenter;
import com.gmugu.happytour.presenter.ISettingPresenter;
import com.gmugu.happytour.presenter.IUserInfoPresenter;
import com.gmugu.happytour.presenter.impl.ModifyUserInfoPresenterImpl;
import com.gmugu.happytour.presenter.impl.SettingPresenterImpl;
import com.gmugu.happytour.presenter.impl.UserInfoPresenterImpl;
import com.gmugu.happytour.user.User;
import com.gmugu.happytour.view.IMainView;
import com.gmugu.happytour.view.IModifyUserInfoView;
import com.gmugu.happytour.view.ISettingView;
import com.gmugu.happytour.view.IUserInfoView;
import com.gmugu.happytour.view.activity.MainActivity;
import com.gmugu.happytour.view.activity.Scope.ActivityScope;
import com.gmugu.happytour.view.fragment.ModifyUserInfoFragment;
import com.gmugu.happytour.view.fragment.SettingFragment;
import com.gmugu.happytour.view.fragment.UserInfoFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mugu on 16-4-9 下午2:41.
 */
@Module
public class MainModule {

    private MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    public IMainPresenter providePresenter() {
        // TODO: 16-4-10
        return null;
    }

    @Provides
    public IMainView provideView() {
        // TODO: 16-4-10
        return null;
    }

    @ActivityScope
    @Provides
    @Named("SettingFragment")
    public Fragment provideSettingFragment() {
        return SettingFragment.newInstance();
    }

    @Provides
    public ISettingView provideSettingView(@Named("SettingFragment") Fragment settingFragment) {
        return (ISettingView) settingFragment;
    }


    @Provides
    public ISettingPresenter provideSettingPresenter(IApiService apiService, ISettingView settingView) {
        return new SettingPresenterImpl(apiService, settingView);
    }

    /******************************************************/

    @ActivityScope
    @Provides
    @Named("ModifyUserInfoFragment")
    public Fragment provideModifyUserInfoFragment() {
        ModifyUserInfoFragment modifyUserInfoFragment = ModifyUserInfoFragment.newInstance(User.getInstance().getUserInfoModel());
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

    /******************************************************/

    @ActivityScope
    @Provides
    @Named("UserInfoFragment")
    public Fragment provideUserInfoFragment() {
        UserInfoFragment userInfoFragment = UserInfoFragment.newInstance(User.getInstance().getUserInfoModel());
        return userInfoFragment;
    }

    @Provides
    public IUserInfoPresenter provodeUserInfoPresenter(IUserInfoView view, IApiService apiService, ISpfManager spfManager, Context context) {
        return new UserInfoPresenterImpl(view, apiService, spfManager, context);
    }

    @Provides
    public IUserInfoView provideUserInfoView(@Named("UserInfoFragment") Fragment fragment) {
        return (IUserInfoView) fragment;
    }


}
