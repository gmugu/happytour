package com.gmugu.happytour.presenter.impl;

import com.gmugu.happytour.presenter.IMainPresenter;
import com.gmugu.happytour.view.IMainView;

/**
 * Created by mugu on 16-4-11 下午9:22.
 */
public class MainPresenterImpl implements IMainPresenter {
    private IMainView view;

    public MainPresenterImpl(IMainView view) {
        this.view = view;
    }

    @Override
    public void onMapRbPressed() {
        view.replaceToMapFragment();
    }

    @Override
    public void onTrackRbPressed() {

    }

    @Override
    public void onChatRbPressed() {

    }

    @Override
    public void onSettingRbPressed() {

    }
}
