package com.gmugu.happytour.view.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.gmugu.happytour.R;
import com.gmugu.happytour.application.AppComponent;
import com.gmugu.happytour.comment.assist.Log;
import com.gmugu.happytour.presenter.IChatPresenter;
import com.gmugu.happytour.presenter.IMainPresenter;
import com.gmugu.happytour.presenter.IModifyUserInfoPresenter;
import com.gmugu.happytour.presenter.IRealTimePresenter;
import com.gmugu.happytour.presenter.IScenicInfoPresenter;
import com.gmugu.happytour.presenter.ISettingPresenter;
import com.gmugu.happytour.presenter.IUserTrackPresenter;
import com.gmugu.happytour.view.IMainView;
import com.gmugu.happytour.view.activity.component.DaggerMainComponent;
import com.gmugu.happytour.view.activity.module.MainModule;
import com.gmugu.happytour.view.fragment.ChatFragment;
import com.gmugu.happytour.view.fragment.ModifyUserInfoFragment;
import com.gmugu.happytour.view.fragment.RealTimeFragment;
import com.gmugu.happytour.view.fragment.ScenicInfoFragment;
import com.gmugu.happytour.view.fragment.SettingFragment;
import com.gmugu.happytour.view.fragment.UserTrackFragment;

import javax.inject.Inject;
import javax.inject.Named;


public class MainActivity extends BasicActivity implements
        IMainView,
        SettingFragment.OnFragmentInteractionListener,
        ModifyUserInfoFragment.OnModifyUserInfoFragmentInteractionListener,
        RealTimeFragment.OnReadTimeFragmentInteractionListener,
        UserTrackFragment.OnUserTrackFragmentInteractionListener,
        ScenicInfoFragment.OnScenicInfoFragmentInteractionListener, ChatFragment.OnChatFragmentInteractionListener {

    @Inject
    protected IMainPresenter mainPresenter;

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
     * RealTimeFragment
     ********************/

    @Inject
    @Named("RealTimeFragment")
    protected Fragment realTimeFragment;
    @Inject
    protected IRealTimePresenter realTimePresenter;

    /********************
     * UserTrackFragment
     ********************/
    @Inject
    @Named("UserTrackFragment")
    protected Fragment userTrackFragment;
    @Inject
    protected IUserTrackPresenter userTrackPresenter;

    /********************
     * ScenicInfoFragment
     ********************/
    @Inject
    @Named("ScenicInfoFragment")
    protected Fragment scenicInfoFragment;
    @Inject
    protected IScenicInfoPresenter scenicInfoPresenter;

    /********************
     * ChatFragment
     ********************/
    @Inject
    @Named("ChatFragment")
    protected Fragment chatFragment;
    @Inject
    protected IChatPresenter chatPresenter;


    private RadioGroup navigationRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void replaceFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.activity_main_context_fl, fragment).commit();
    }

    private void initView() {
        navigationRg = (RadioGroup) findViewById(R.id.activity_main_navigation_rg);
        navigationRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); ++i) {
                    View childAt = group.getChildAt(i);
                    if (childAt.getId() == checkedId) {
                        childAt.setBackgroundColor(0xffffffff);
                    } else {
                        childAt.setBackgroundColor(0xff9f9f9f);
                    }
                }
                switch (checkedId) {
                    case R.id.activity_main_map_rb:
                        mainPresenter.onMapRbPressed();
                        break;
                    case R.id.activity_main_track_rb:
                        mainPresenter.onTrackRbPressed();
                        break;
                    case R.id.activity_main_chat_rb:
                        mainPresenter.onChatRbPressed();
                        break;
                    case R.id.activity_main_setting_rb:
                        mainPresenter.onSettingRbPressed();
                        break;
                }

            }
        });
        navigationRg.check(R.id.activity_main_map_rb);
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).mainModule(new MainModule(this)).build().inject(this);
    }


    @Override
    public void toModifyUserInfo() {
        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.root_view, modifyFragment).commit();
    }

    @Override
    public ISettingPresenter getSettingPresenter() {
        Log.e(this, settingFragment != null);
        return settingPresenter;
    }

    @Override
    public IModifyUserInfoPresenter getModifyUserInfoPresenter() {
        return modifyUserInfoPresenter;
    }

    @Override
    public IRealTimePresenter getRealTimePresenter() {
        return realTimePresenter;
    }

    @Override
    public ScenicInfoFragment getScenicInfoFragment() {
        return (ScenicInfoFragment) scenicInfoFragment;
    }

    @Override
    public IUserTrackPresenter getUserTrackPresenter() {
        return userTrackPresenter;
    }

    @Override
    public IScenicInfoPresenter getScenicInfoPresenter() {
        return scenicInfoPresenter;
    }

    @Override
    public IChatPresenter getChatPresenter() {
        return chatPresenter;
    }

    @Override
    public void replaceToMapFragment() {
        replaceFragment(realTimeFragment);
    }

    @Override
    public void replaceToTrackFragment() {
        replaceFragment(userTrackFragment);
    }

    @Override
    public void replaceToChatFragment() {
        replaceFragment(chatFragment);
    }

    @Override
    public void replaceToSettingFragment() {
        replaceFragment(settingFragment);
    }
}
