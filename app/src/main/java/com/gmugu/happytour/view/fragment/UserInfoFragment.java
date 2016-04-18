package com.gmugu.happytour.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmugu.happytour.R;
import com.gmugu.happyhour.message.UserInfoModel;
import com.gmugu.happytour.presenter.IUserInfoPresenter;
import com.gmugu.happytour.view.IUserInfoView;

public class UserInfoFragment extends Fragment implements IUserInfoView{

    private final static String ARG_USER_INFO = "userinfo";
    private UserInfoModel userInfoModel;

    private OnUserInfoFragmentInteractionListener mListener;
    private View mView;
    private IUserInfoPresenter userInfoPresenter;

    public UserInfoFragment() {
        // Required empty public constructor
    }

    public static UserInfoFragment newInstance(UserInfoModel userInfoModel) {
        UserInfoFragment fragment = new UserInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_INFO, userInfoModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        userInfoModel = (UserInfoModel) args.getSerializable(ARG_USER_INFO);
        if (userInfoModel == null) {
            userInfoModel = new UserInfoModel();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_user_info, container, false);
        initView();
        return mView;
    }

    private void initView() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnUserInfoFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnUserInfoFragmentInteractionListener");
        }
        userInfoPresenter = mListener.getUserInfoPresenter();
        if (userInfoPresenter == null) {
            throw new NullPointerException("userInfoPresenter must be not null");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnUserInfoFragmentInteractionListener {
        IUserInfoPresenter getUserInfoPresenter();
    }

}
