package com.gmugu.happytour.view.activity.component;

import com.gmugu.happytour.application.AppComponent;
import com.gmugu.happytour.view.activity.RegisterActivity;
import com.gmugu.happytour.view.activity.Scope.ActivityScope;
import com.gmugu.happytour.view.activity.module.RegisterModule;

import dagger.Component;

/**
 * Created by mugu on 16-4-9 下午2:43.
 */
@ActivityScope
@Component(modules = RegisterModule.class, dependencies = AppComponent.class)
public interface RegisterComponent {

    void inject(RegisterActivity activity);

}
