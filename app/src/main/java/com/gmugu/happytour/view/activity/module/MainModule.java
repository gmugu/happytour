package com.gmugu.happytour.view.activity.module;

import android.app.Fragment;
import android.content.Context;

import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happytour.data.spf.ISpfManager;
import com.gmugu.happytour.presenter.IMainPresenter;
import com.gmugu.happytour.presenter.IModifyUserInfoPresenter;
import com.gmugu.happytour.presenter.IRealTimePresenter;
import com.gmugu.happytour.presenter.ISettingPresenter;
import com.gmugu.happytour.presenter.IUserInfoPresenter;
import com.gmugu.happytour.presenter.impl.MainPresenterImpl;
import com.gmugu.happytour.presenter.impl.ModifyUserInfoPresenterImpl;
import com.gmugu.happytour.presenter.impl.RealTimePresenterImpl;
import com.gmugu.happytour.presenter.impl.SettingPresenterImpl;
import com.gmugu.happytour.presenter.impl.UserInfoPresenterImpl;
import com.gmugu.happytour.user.User;
import com.gmugu.happytour.view.IMainView;
import com.gmugu.happytour.view.IMapView;
import com.gmugu.happytour.view.IModifyUserInfoView;
import com.gmugu.happytour.view.IRealTimeView;
import com.gmugu.happytour.view.ISettingView;
import com.gmugu.happytour.view.IUserInfoView;
import com.gmugu.happytour.view.activity.MainActivity;
import com.gmugu.happytour.view.activity.Scope.ActivityScope;
import com.gmugu.happytour.view.fragment.ModifyUserInfoFragment;
import com.gmugu.happytour.view.fragment.RealTimeFragment;
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
    public IMainPresenter providePresenter(IMainView view) {
        return new MainPresenterImpl(view);
    }

    @Provides
    public IMainView provideView() {
        return mainActivity;
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
    @Named("RealTimeFragment")
    public Fragment provideRealTimeFragment() {
        RealTimeFragment realTimeFragment = RealTimeFragment.newInstance();
        return realTimeFragment;
    }

    @Provides
    public IRealTimePresenter provodeRealTimePresenter(IRealTimeView view, IApiService apiService, ISpfManager spfManager, Context context) {
        return new RealTimePresenterImpl(view, apiService);
    }

    @Provides
    public IRealTimeView provideUserInfoView(@Named("RealTimeFragment") Fragment fragment) {
        return (IRealTimeView) fragment;
    }


}
