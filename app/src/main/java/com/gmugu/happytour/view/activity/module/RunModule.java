package com.gmugu.happytour.view.activity.module;

import android.content.Context;

import com.gmugu.happytour.presenter.IRunningPresenter;
import com.gmugu.happytour.presenter.impl.RunningPresenterImpl;
import com.gmugu.happytour.view.IRunningView;
import com.gmugu.happytour.view.activity.RunActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mugu on 16-4-9 下午2:41.
 */
@Module
public class RunModule {


    private RunActivity runActivity;

    public RunModule(RunActivity runActivity) {
        this.runActivity = runActivity;
    }

    @Provides
    public IRunningPresenter providePresenter(IRunningView view, Context context) {
        return new RunningPresenterImpl(view, context);
    }

    @Provides
    public IRunningView provideView() {
        return runActivity;
    }

}
