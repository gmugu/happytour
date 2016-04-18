package com.gmugu.happytour.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.gmugu.happytour.R;
import com.gmugu.happytour.application.AppComponent;
import com.gmugu.happytour.presenter.IModifyUserInfoPresenter;
import com.gmugu.happytour.presenter.IRegisterPresenter;
import com.gmugu.happytour.view.activity.component.DaggerRegisterComponent;
import com.gmugu.happytour.view.activity.module.RegisterModule;
import com.gmugu.happytour.view.fragment.ModifyUserInfoFragment;
import com.gmugu.happytour.view.fragment.RegisterFragment.OnRegisterFragmentInteractionListener;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by mugu on 16-4-9 下午12:48.
 */
public class RegisterActivity extends BasicActivity implements OnRegisterFragmentInteractionListener, ModifyUserInfoFragment.OnModifyUserInfoFragmentInteractionListener {

    private FragmentManager fragmentManager;

    @Inject
    @Named("RegisterFragment")
    protected Fragment registerFragment;

    @Inject
    @Named("ModifyUserInfoFragment")
    protected Fragment modifyUserInfoFragment;

    @Inject
    protected IRegisterPresenter registerPresenter;

    @Inject
    protected IModifyUserInfoPresenter modifyUserInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }

    private void initView() {
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_register_framelayout, registerFragment).commit();

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRegisterComponent.builder().appComponent(appComponent).registerModule(new RegisterModule(this)).build().inject(this);
    }


    @Override
    public IRegisterPresenter getRegisterPresenter() {
        return registerPresenter;
    }

    @Override
    public void toModifyUserInfo() {
        fragmentManager.beginTransaction().replace(R.id.activity_register_framelayout, modifyUserInfoFragment).addToBackStack(null).commit();
    }

    @Override
    public IModifyUserInfoPresenter getModifyUserInfoPresenter() {
        return modifyUserInfoPresenter;
    }

}
