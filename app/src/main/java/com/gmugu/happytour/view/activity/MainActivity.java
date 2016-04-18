package com.gmugu.happytour.view.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import com.gmugu.happytour.R;
import com.gmugu.happytour.application.AppComponent;
import com.gmugu.happytour.comment.assist.Log;
import com.gmugu.happytour.comment.constant.ActivityActionName;
import com.gmugu.happytour.presenter.IModifyUserInfoPresenter;
import com.gmugu.happytour.presenter.ISettingPresenter;
import com.gmugu.happytour.presenter.IUserInfoPresenter;
import com.gmugu.happytour.view.IMainView;
import com.gmugu.happytour.view.activity.component.DaggerMainComponent;
import com.gmugu.happytour.view.activity.module.MainModule;
import com.gmugu.happytour.view.fragment.ModifyUserInfoFragment;
import com.gmugu.happytour.view.fragment.SettingFragment;
import com.gmugu.happytour.view.fragment.UserInfoFragment;

import javax.inject.Inject;
import javax.inject.Named;


public class MainActivity extends BasicActivity implements IMainView, SettingFragment.OnFragmentInteractionListener, ModifyUserInfoFragment.OnModifyUserInfoFragmentInteractionListener, UserInfoFragment.OnUserInfoFragmentInteractionListener {

    /********************
     * Setting
     ********************/

    @Inject
    @Named("SettingFragment")
    protected Fragment settingFragment;
    @Inject
    protected ISettingPresenter settingPresenter;

    /********************
     * ModifyUserInfo
     ********************/
    @Inject
    @Named("ModifyUserInfoFragment")
    protected Fragment modifyFragment;

    @Inject
    protected IModifyUserInfoPresenter modifyUserInfoPresenter;

    /********************
     * UserInfo
     ********************/

    @Inject
    @Named("UserInfoFragment")
    protected Fragment userInfoFragment;

    @Inject
    protected IUserInfoPresenter userInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(R.id.root_view, userInfoFragment).commit();
        if (true) {
            Intent i = new Intent(ActivityActionName.LOGIN_ACTIVITY_ACTION);
            startActivity(i);
        }

    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).mainModule(new MainModule(this)).build().inject(this);
    }


    @Override
    public ISettingPresenter getSettingPresenter() {
        Log.e(this, settingFragment != null);
        return settingPresenter;
    }

    @Override
    public void toModifyUserInfo() {
        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.root_view, modifyFragment).commit();
    }

    @Override
    public IModifyUserInfoPresenter getModifyUserInfoPresenter() {
        return modifyUserInfoPresenter;
    }

    @Override
    public IUserInfoPresenter getUserInfoPresenter() {
        return userInfoPresenter;
    }
}
