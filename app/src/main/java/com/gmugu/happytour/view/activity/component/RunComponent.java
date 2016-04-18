package com.gmugu.happytour.view.activity.component;

import com.gmugu.happytour.application.AppComponent;
import com.gmugu.happytour.view.activity.RunActivity;
import com.gmugu.happytour.view.activity.Scope.ActivityScope;
import com.gmugu.happytour.view.activity.module.RunModule;

import dagger.Component;

/**
 * Created by mugu on 16-4-9 下午2:43.
 */
@ActivityScope
@Component(modules = RunModule.class, dependencies = AppComponent.class)
public interface RunComponent {

    void inject(RunActivity activity);

}
