package com.gmugu.happytour.view.activity.component;

import com.gmugu.happytour.application.AppComponent;
import com.gmugu.happytour.view.activity.LoginActivity;
import com.gmugu.happytour.view.activity.Scope.ActivityScope;
import com.gmugu.happytour.view.activity.module.LoginModule;

import dagger.Component;

/**
 * Created by mugu on 16-4-9 下午2:43.
 */
@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

}
