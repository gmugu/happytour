package com.gmugu.happytour.view.activity.module;

import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happytour.data.spf.ISpfManager;
import com.gmugu.happytour.presenter.ILoginPresenter;
import com.gmugu.happytour.presenter.impl.LoginPresenterImpl;
import com.gmugu.happytour.view.ILoginView;
import com.gmugu.happytour.view.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mugu on 16-4-9 下午2:41.
 */
@Module
public class LoginModule {

    private LoginActivity loginActivity;

    public LoginModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @Provides
    public ILoginPresenter providePresenter(ILoginView view, IApiService apiService,ISpfManager spfManager) {
        return new LoginPresenterImpl(view, apiService,spfManager);
    }

    @Provides
    public ILoginView provideView() {
        return loginActivity;
    }
}
