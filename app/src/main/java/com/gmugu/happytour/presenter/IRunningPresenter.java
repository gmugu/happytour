package com.gmugu.happytour.presenter;

/**
 * Created by mugu on 16-4-6 下午4:12.
 */
public interface IRunningPresenter {

    void afterCreateView();

    void onDestroy();

    void onStartRunBnPressed();

    void onPauseRunBnPressed();

    void onStopRunBnPressed();

    void onResumeRunBnPressed();

    void back();
}
