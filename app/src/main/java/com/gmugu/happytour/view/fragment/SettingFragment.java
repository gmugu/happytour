package com.gmugu.happytour.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gmugu.happytour.R;
import com.gmugu.happytour.presenter.ISettingPresenter;
import com.gmugu.happytour.view.ISettingView;


public class SettingFragment extends Fragment implements View.OnClickListener, ISettingView {


    private Button modifyUserInfoBn;
    private Button connectDeviceBn;
    private Button checkUpdateBn;
    private Button cleanCacheBn;
    private Button aboutBn;
    private Button logoutBn;

    private OnFragmentInteractionListener mListener;
    private View mView;
    private ISettingPresenter settingPresenter;

    public SettingFragment() {
    }


    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initView() {
        modifyUserInfoBn = (Button) findViewById(R.id.fragment_setting_modify_userinfo_bn);
        modifyUserInfoBn.setOnClickListener(this);
        connectDeviceBn = (Button) findViewById(R.id.fragment_setting_connect_device_bn);
        connectDeviceBn.setOnClickListener(this);
        checkUpdateBn = (Button) findViewById(R.id.fragment_setting_check_update_bn);
        checkUpdateBn.setOnClickListener(this);
        cleanCacheBn = (Button) findViewById(R.id.fragment_setting_clean_cache_bn);
        cleanCacheBn.setOnClickListener(this);
        aboutBn = (Button) findViewById(R.id.fragment_setting_about_bn);
        aboutBn.setOnClickListener(this);
        logoutBn = (Button) findViewById(R.id.fragment_setting_logout_bn);
        logoutBn.setOnClickListener(this);
    }

    private View findViewById(int id) {
        return mView.findViewById(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_setting, container, false);
        initView();
        return mView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnUserInfoFragmentInteractionListener");
        }
        settingPresenter = mListener.getSettingPresenter();
        if (settingPresenter == null) {
            throw new NullPointerException("settingPresenter must be not null");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_setting_modify_userinfo_bn:
                settingPresenter.onModifyUserinfo();
                break;
            case R.id.fragment_setting_connect_device_bn:
                settingPresenter.onConnectDevice();
                break;
            case R.id.fragment_setting_check_update_bn:
                settingPresenter.onCheckUpdate();
                break;
            case R.id.fragment_setting_clean_cache_bn:
                settingPresenter.onCleanCache();
                break;
            case R.id.fragment_setting_about_bn:
                settingPresenter.onAbout();
                break;
            case R.id.fragment_setting_logout_bn:
                settingPresenter.onLogout();
                break;
            default:
                break;
        }
    }

    @Override
    public void toModifyUserinfo() {
        mListener.toModifyUserInfo();
    }

    @Override
    public void showErrorDialog() {

    }

    public interface OnFragmentInteractionListener {

        ISettingPresenter getSettingPresenter();

        void toModifyUserInfo();
    }

}
